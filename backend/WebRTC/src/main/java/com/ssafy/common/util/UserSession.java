package com.ssafy.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kurento.client.Continuation;
import org.kurento.client.EventListener;
import org.kurento.client.IceCandidate;
import org.kurento.client.IceCandidateFoundEvent;
import org.kurento.client.ImageOverlayFilter;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;
import org.kurento.jsonrpc.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.JsonObject;

public class UserSession implements Closeable {

	private static final Logger log = LoggerFactory.getLogger(UserSession.class);

	private final String name;

	private final WebSocketSession session;

	private final MediaPipeline pipeline;

	private final String roomName;
	private final WebRtcEndpoint outgoingMedia;
	private final ConcurrentMap<String, WebRtcEndpoint> incomingMedia = new ConcurrentHashMap<>();

	public UserSession(final String name, String roomName, final WebSocketSession session, MediaPipeline pipeline) {
		this.pipeline = pipeline;
		this.name = name;
		this.roomName = roomName;
		this.session = session;
		this.outgoingMedia = new WebRtcEndpoint.Builder(pipeline).build();

		// 생성자에서 outgoingMedia를 incomingMedia에 포함
		this.incomingMedia.put(name, outgoingMedia);
		this.outgoingMedia.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

			@Override
			public void onEvent(IceCandidateFoundEvent event) {
				JsonObject response = new JsonObject();
				response.addProperty("id", "iceCandidate");
				response.addProperty("name", name);
				response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
				try {
					synchronized (session) {
						session.sendMessage(new TextMessage(response.toString()));
					}
				} catch (IOException e) {
					log.debug(e.getMessage());
				}
			}
		});
	}

	public WebRtcEndpoint getOutgoingWebRtcPeer() {
		return outgoingMedia;
	}

	public String getName() {
		return name;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setPresenter() throws IOException {
		JsonObject presenterParams = new JsonObject();
		presenterParams.addProperty("id", "startPresentation");
		presenterParams.addProperty("presenter", this.getName());

		this.sendMessage(presenterParams);
	}

	public WebRtcEndpoint getIncomingMedia(String name) {
		return incomingMedia.get(name);
	}

	public MediaPipeline getPipeline() {
		return pipeline;
	}

	public void receiveVideoFrom(UserSession sender, String sdpOffer) throws IOException {
		log.info("USER {}: connecting with {} in room {}", this.name, sender.getName(), this.roomName);

		log.trace("USER {}: SdpOffer for {} is {}", this.name, sender.getName(), sdpOffer);

		String ipSdpAnswer;
		JsonObject scParams = new JsonObject();

		// 본인일 경우 자신의 outgoing과 incoming 연결
		if (sender.equals(this)) {
			log.info("sender == this - processOffer");
			ipSdpAnswer = this.getOutgoingWebRtcPeer().processOffer(sdpOffer);
		} else {
			log.info("sender != this - processOffer");
			ipSdpAnswer = this.getEndpointForUser(sender).processOffer(sdpOffer);
		}

		scParams.addProperty("id", "receiveVideoAnswer");
		scParams.addProperty("name", sender.getName());
		scParams.addProperty("sdpAnswer", ipSdpAnswer);

		log.trace("USER {}: SdpAnswer for {} is {}", this.name, sender.getName(), ipSdpAnswer);
		this.sendMessage(scParams);

		log.debug("gather candidates");
		if (sender.equals(this)) {
			log.info("sender == this - connect");
			this.getOutgoingWebRtcPeer().gatherCandidates();
			this.getOutgoingWebRtcPeer().connect(incomingMedia.get(this.name));
		} else {
			log.info("sender != this - connect");
			this.getEndpointForUser(sender).gatherCandidates();
		}

		WebRtcEndpoint incoming = incomingMedia.get(sender.getName());
		sender.getOutgoingWebRtcPeer().connect(incoming);
	}

	/*
	 * ImageOverlayPipeline 연결
	 */
	public void linkImageOverlayPipeline(UserSession sender, ImageOverlayFilter imageOverlayFilter) {
		WebRtcEndpoint incoming = incomingMedia.get(sender.getName());
		log.info("[linkImageOverlayPipeline] sender: {} ImageOverlayPipeline connect to {}", sender.getName(),
				this.getName());
		sender.getOutgoingWebRtcPeer().connect(imageOverlayFilter);
		imageOverlayFilter.connect(incoming);

	}

	private WebRtcEndpoint getEndpointForUser(final UserSession sender) {

		WebRtcEndpoint incoming = incomingMedia.get(sender.getName());

		if (incoming == null) {
			log.debug("PARTICIPANT {}: creating new endpoint for {}", this.name, sender.getName());
			incoming = new WebRtcEndpoint.Builder(pipeline).build();

			incoming.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

				@Override
				public void onEvent(IceCandidateFoundEvent event) {
					JsonObject response = new JsonObject();
					response.addProperty("id", "iceCandidate");
					response.addProperty("name", sender.getName());
					response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
					try {
						synchronized (session) {
							session.sendMessage(new TextMessage(response.toString()));
						}
					} catch (IOException e) {
						log.debug(e.getMessage());
					}
				}
			});

			incomingMedia.put(sender.getName(), incoming);
		}

		log.debug("PARTICIPANT {}: obtained endpoint for {}", this.name, sender.getName());

		log.info("[getEndpointForUser] incoming: {}", incoming);

		return incoming;
	}

	public void cancelVideoFrom(final UserSession sender) {
		this.cancelVideoFrom(sender.getName());
	}

	public void cancelVideoFrom(final String senderName) {
		log.debug("PARTICIPANT {}: canceling video reception from {}", this.name, senderName);
		final WebRtcEndpoint incoming = incomingMedia.remove(senderName);

		log.debug("PARTICIPANT {}: removing endpoint for {}", this.name, senderName);
		incoming.release(new Continuation<Void>() {
			@Override
			public void onSuccess(Void result) throws Exception {
				log.trace("PARTICIPANT {}: Released successfully incoming EP for {}", UserSession.this.name,
						senderName);
			}

			@Override
			public void onError(Throwable cause) throws Exception {
				log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserSession.this.name, senderName);
			}
		});
	}

	@Override
	public void close() throws IOException {
		log.debug("PARTICIPANT {}: Releasing resources", this.name);
		for (final String remoteParticipantName : incomingMedia.keySet()) {

			log.trace("PARTICIPANT {}: Released incoming EP for {}", this.name, remoteParticipantName);

			final WebRtcEndpoint ep = this.incomingMedia.get(remoteParticipantName);

			ep.release(new Continuation<Void>() {

				@Override
				public void onSuccess(Void result) throws Exception {
					log.trace("PARTICIPANT {}: Released successfully incoming EP for {}", UserSession.this.name,
							remoteParticipantName);
				}

				@Override
				public void onError(Throwable cause) throws Exception {
					log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserSession.this.name,
							remoteParticipantName);
				}
			});
		}

		outgoingMedia.release(new Continuation<Void>() {

			@Override
			public void onSuccess(Void result) throws Exception {
				log.trace("PARTICIPANT {}: Released outgoing EP", UserSession.this.name);
			}

			@Override
			public void onError(Throwable cause) throws Exception {
				log.warn("USER {}: Could not release outgoing EP", UserSession.this.name);
			}
		});
	}

	public void sendMessage(JsonObject message) throws IOException {
		log.debug("USER {}: Sending message {}", name, message);
		synchronized (session) {
			session.sendMessage(new TextMessage(message.toString()));
		}
	}

	public void addCandidate(IceCandidate candidate, String name) {
		if (this.name.compareTo(name) == 0) {
			outgoingMedia.addIceCandidate(candidate);
		} else {
			WebRtcEndpoint webRtc = incomingMedia.get(name);
			if (webRtc != null) {
				webRtc.addIceCandidate(candidate);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof UserSession)) {
			return false;
		}
		UserSession other = (UserSession) obj;
		boolean eq = name.equals(other.name);
		eq &= this.session.getId().equals(other.getSession().getId());
		eq &= roomName.equals(other.roomName);
		return eq;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + name.hashCode();
		result = 31 * result + roomName.hashCode();
		return result;
	}

}
