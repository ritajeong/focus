package com.ssafy.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PreDestroy;

import org.kurento.client.Continuation;
import org.kurento.client.MediaPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Room implements Closeable {
	private final Logger log = LoggerFactory.getLogger(Room.class);

	private final ConcurrentMap<String, UserSession> participants = new ConcurrentHashMap<>();
	private final MediaPipeline pipeline;
	private final String name;
	private final String owner;
	private Presentation presentation;

	public String getName() {
		return name;
	}

	public MediaPipeline getPipeline() {
		return pipeline;
	}

	public Room(String roomName, MediaPipeline pipeline, String owner) {
		this.name = roomName;
		this.pipeline = pipeline;
		this.owner = owner;
		this.presentation = new Presentation(null, owner, null, null, null, null);
		log.info("ROOM {} has been created", roomName);
	}

	public void setPresenter(String presenterName) throws IOException {
		presentation.setPresenterName(presenterName);
		presentation.setPresentationTransition(null);
		presentation.setPresentationCurrentPage(null);
		presentation.setPresentationLocation(null);
		presentation.setPresentationSize(null);

		final JsonObject presenterChangeMsg = new JsonObject();
		presenterChangeMsg.addProperty("id", "changePresenter");
		presenterChangeMsg.addProperty("presenter", presenterName);
		for (UserSession participant : participants.values()) {
			participant.sendMessage(presenterChangeMsg);
		}
	}

	public void setPresentation(String presentationUserId, String presentationTransition,
			String presentationCurrentPage, String presentationLocation, String presentationSize) {
		presentation.setPresentationUserId(presentationUserId);
		presentation.setPresentationTransition(presentationTransition);
		presentation.setPresentationCurrentPage(presentationCurrentPage);
		presentation.setPresentationLocation(presentationLocation);
		presentation.setPresentationSize(presentationSize);
	}

	public String getOwner() {
		return owner;
	}

	public String getPresentationUserId() {
		return presentation.getPresentationUserId();
	}

	public String getPresentationTransition() {
		return presentation.getPresentationTransition();
	}

	public String getPresentationCurrentPage() {
		return presentation.getPresentationCurrentPage();
	}

	public String getPresentationLocation() {
		return presentation.getPresentationLocation();
	}

	public String getPresentationSize() {
		return presentation.getPresentationSize();
	}

	public String getPresenterName() {
		return presentation.getPresenterName();
	}

	@PreDestroy
	private void shutdown() {
		this.close();
	}

	public UserSession join(String userName, WebSocketSession session) throws IOException {
		log.info("ROOM {}: adding participant {}", this.name, userName);
		final UserSession participant = new UserSession(userName, this.name, session, this.pipeline);
		joinRoom(participant);
		participants.put(participant.getName(), participant);
		sendParticipantNames(participant);
		return participant;
	}

	public void leave(UserSession user) throws IOException {
		log.debug("PARTICIPANT {}: Leaving room {}", user.getName(), this.name);
		this.removeParticipant(user.getName());
		user.close();
	}

	private Collection<String> joinRoom(UserSession newParticipant) throws IOException {
		final JsonObject newParticipantMsg = new JsonObject();
		newParticipantMsg.addProperty("id", "newParticipantArrived");
		newParticipantMsg.addProperty("name", newParticipant.getName());

		final List<String> participantsList = new ArrayList<>(participants.values().size());
		log.debug("ROOM {}: notifying other participants of new participant {}", name, newParticipant.getName());

		for (final UserSession participant : participants.values()) {
			try {
				participant.sendMessage(newParticipantMsg);
			} catch (final IOException e) {
				log.debug("ROOM {}: participant {} could not be notified", name, participant.getName(), e);
			}
			participantsList.add(participant.getName());
		}

		return participantsList;
	}

	private void removeParticipant(String name) throws IOException {
		participants.remove(name);

		log.info("ROOM {}: notifying all users that {} is leaving the room", this.name, name);

		final List<String> unnotifiedParticipants = new ArrayList<>();
		final JsonObject participantLeftJson = new JsonObject();
		participantLeftJson.addProperty("id", "participantLeft");
		participantLeftJson.addProperty("name", name);
		for (final UserSession participant : participants.values()) {
			try {
				participant.cancelVideoFrom(name);
				participant.sendMessage(participantLeftJson);
			} catch (final IOException e) {
				unnotifiedParticipants.add(participant.getName());
			}
		}

		if (!unnotifiedParticipants.isEmpty()) {
			log.debug("ROOM {}: The users {} could not be notified that {} left the room", this.name,
					unnotifiedParticipants, name);
		}

	}

	public void sendParticipantNames(UserSession user) throws IOException {

		final JsonArray participantsArray = new JsonArray();
		for (final UserSession participant : this.getParticipants()) {
			if (!participant.equals(user)) {
				final JsonElement participantName = new JsonPrimitive(participant.getName());
				participantsArray.add(participantName);
			}
		}

		final JsonObject existingParticipantsMsg = new JsonObject();
		existingParticipantsMsg.addProperty("id", "existingParticipants");
		existingParticipantsMsg.add("data", participantsArray);
		existingParticipantsMsg.addProperty("presenter", presentation.getPresenterName());
		existingParticipantsMsg.addProperty("presentationUserId", presentation.getPresentationUserId());
		existingParticipantsMsg.addProperty("presentationTransition", presentation.getPresentationTransition());
		existingParticipantsMsg.addProperty("currentPage", presentation.getPresentationCurrentPage());
		existingParticipantsMsg.addProperty("location", presentation.getPresentationLocation());
		existingParticipantsMsg.addProperty("size", presentation.getPresentationSize());
		log.debug("PARTICIPANT {}: sending a list of {} participants", user.getName(), participantsArray.size());
		user.sendMessage(existingParticipantsMsg);
	}

	public Collection<UserSession> getParticipants() {
		return participants.values();
	}

	public UserSession getParticipant(String name) {
		return participants.get(name);
	}

	@Override
	public void close() {
		for (final UserSession user : participants.values()) {
			try {
				user.close();
			} catch (IOException e) {
				log.debug("ROOM {}: Could not invoke close on participant {}", this.name, user.getName(), e);
			}
		}

		participants.clear();

		pipeline.release(new Continuation<Void>() {

			@Override
			public void onSuccess(Void result) throws Exception {
				log.trace("ROOM {}: Released Pipeline", Room.this.name);
			}

			@Override
			public void onError(Throwable cause) throws Exception {
				log.warn("PARTICIPANT {}: Could not release Pipeline", Room.this.name);
			}
		});

		log.debug("Room {} closed", this.name);
	}

}
