package com.ssafy.api.controller;

import java.io.IOException;

import org.kurento.client.IceCandidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ssafy.api.service.RoomManager;
import com.ssafy.common.util.Room;
import com.ssafy.common.util.UserRegistry;
import com.ssafy.common.util.UserSession;

public class CallHandler extends TextWebSocketHandler {

	private static final Logger log = LoggerFactory.getLogger(CallHandler.class);

	private static final Gson gson = new GsonBuilder().create();

	@Autowired
	private RoomManager roomManager;

	@Autowired
	private UserRegistry registry;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		final JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);

		final UserSession user = registry.getBySession(session);

		if (user != null) {
			log.debug("Incoming message from user '{}': {}", user.getName(), jsonMessage);
		} else {
			log.debug("Incoming message from new user: {}", jsonMessage);
		}

		switch (jsonMessage.get("id").getAsString()) {
		case "joinRoom":
			joinRoom(jsonMessage, session);
			break;
		case "receiveVideoFrom":
			final String senderName = jsonMessage.get("sender").getAsString();
			final UserSession sender = registry.getByName(senderName);
			final String sdpOffer = jsonMessage.get("sdpOffer").getAsString();
			user.receiveVideoFrom(sender, sdpOffer);
			break;
		case "leaveRoom": {
			leaveRoom(user);
			break;
		}
		case "onIceCandidate": {
			JsonObject candidate = jsonMessage.get("candidate").getAsJsonObject();

			if (user != null) {
				IceCandidate cand = new IceCandidate(candidate.get("candidate").getAsString(),
						candidate.get("sdpMid").getAsString(), candidate.get("sdpMLineIndex").getAsInt());
				user.addCandidate(cand, jsonMessage.get("name").getAsString());
			}
			break;
		}
		case "setPresenter": {
			log.trace("setPresenter");
			setPresenter(jsonMessage);
			break;
		}
		case "changeContent": {
			log.trace("changeContent");
			UserSession userSession = registry.getBySession(session);
			Room room = roomManager.getRoom(userSession.getRoomName(), userSession.getName());
			for (UserSession participant : room.getParticipants()) {
				participant.sendMessage(jsonMessage);
			}

			String presentationUserId = jsonMessage.get("presentationUserId").getAsString();
			room.setPresentation(presentationUserId, room.getPresentationTransition(), room.getPresentationCurrentPage(),
					room.getPresentationLocation(), room.getPresentationSize());
			break;
		}
		case "changePresentation": {
			log.trace("changePresentation");
			UserSession userSession = registry.getBySession(session);
			Room room = roomManager.getRoom(userSession.getRoomName(), userSession.getName());
			for (UserSession participant : room.getParticipants()) {
				participant.sendMessage(jsonMessage);
			}
			String presentationTransition = jsonMessage.get("transition").getAsString();
			String presentationCurrentPage = jsonMessage.get("currentPage").getAsString();
			String presentationLocation = jsonMessage.get("location").getAsString();
			String presentationSize = jsonMessage.get("size").getAsString();
			room.setPresentation(room.getPresentationUserId(), presentationTransition, presentationCurrentPage,
					presentationLocation, presentationSize);
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		UserSession user = registry.removeBySession(session);
		if (user != null) {
			Room room = roomManager.getRoom(user.getRoomName(), user.getName());
			room.leave(user);
			log.info("(User){} is removed from (Room){}", user.getName(), user.getRoomName());
			if (room.getParticipants().isEmpty()) {
				roomManager.removeRoom(room);
			} else if (user.getName().equals(room.getOwner())) {
				for (UserSession userSession : room.getParticipants()) {
					System.out.println("userSessionName : " + userSession.getName());
					room.leave(userSession);
					registry.removeBySession(userSession.getSession());
				}
			} else if (user.getName().equals(room.getPresenterName())) {
				roomManager.setPresenter(room.getName(), room.getOwner());
			}
		}
	}

	private void joinRoom(JsonObject params, WebSocketSession session) throws IOException {
		final String roomName = params.get("room").getAsString();
		final String name = params.get("name").getAsString();
		log.info("PARTICIPANT {}: trying to join room {}", name, roomName);

		Room room = roomManager.getRoom(roomName, name);
		final UserSession user = room.join(name, session);
		registry.register(user);
	}

	private void leaveRoom(UserSession user) throws IOException {
		System.out.println("user.getRoomName : " + user.getRoomName() + " user.getName : " + user.getName());
		final Room room = roomManager.getRoom(user.getRoomName(), user.getName());
		room.leave(user);
		registry.removeBySession(user.getSession());
		log.info("(User){} is removed from (Room){}", user.getName(), user.getRoomName());
		if (room.getParticipants().isEmpty()) {
			roomManager.removeRoom(room);
		} else if (user.getName().equals(room.getOwner())) {
			for (UserSession userSession : room.getParticipants()) {
				System.out.println("userSessionName : " + userSession.getName());
				room.leave(userSession);
				registry.removeBySession(userSession.getSession());
			}
			roomManager.removeRoom(room);
		} else if (user.getName().equals(room.getPresenterName())) {
			roomManager.setPresenter(room.getName(), room.getOwner());
		}
	}

	private void setPresenter(JsonObject params) throws IOException {
		String presenterName = params.get("presenter").getAsString();
		UserSession presenter = registry.getByName(presenterName);
		roomManager.setPresenter(presenter.getRoomName(), presenterName);
	}
}
