package com.ssafy.common.util;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

public class UserRegistry {

	private final ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();

	public void register(UserSession user) {
		usersByName.put(user.getName(), user);
		usersBySessionId.put(user.getSession().getId(), user);
	}

	public ConcurrentHashMap<String, UserSession> getUsersByName() {
		return usersByName;
	}

	public ConcurrentHashMap<String, UserSession> getUsersBySessionId() {
		return usersBySessionId;
	}

	public UserSession getByName(String name) {
		return usersByName.get(name);
	}

	public UserSession getBySession(WebSocketSession session) {
		return usersBySessionId.get(session.getId());
	}

	public boolean exists(String name) {
		return usersByName.keySet().contains(name);
	}

	public UserSession removeBySession(WebSocketSession session) {
		UserSession user = getBySession(session);
		if (user != null) {
			usersByName.remove(user.getName());
			usersBySessionId.remove(session.getId());
		}
		return user;
	}
}