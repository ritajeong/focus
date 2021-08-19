package com.example.demo.db.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rooms {
	@Column(name = "room_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int roomId;
	String name;
	String description;
	@Column(name = "startTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp startTime;
	@Column(name = "endTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp endTime;

	@Column(name = "onLive", columnDefinition = "BOOLEAN DEFAULT FALSE")
	Boolean onLive;

	@OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participants> participant = new ArrayList<>();

	@OneToMany(mappedBy = "rooms", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomsPresentations> presentation = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	Users users;

	@Override
	public String toString() {
		String ret = "Rooms{" + "roomId=" + roomId + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", onLive=" + onLive + ", participant=";
		for (Participants p : participant) {
			ret += p.getUsers().getName() + ", ";
		}
		ret += ", presentation=";
		for (RoomsPresentations rp : presentation) {
			ret += rp.getGroupId() + ", ";
		}
		ret += ", users=" + users + '}';
		return ret;
	}
}
