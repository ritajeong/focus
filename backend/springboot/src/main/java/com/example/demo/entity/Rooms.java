package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rooms{
	@Column(name="room_id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int roomId;
	String name;
	String description;
	@Column(name="startTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp startTime;
	@Column(name="endTime", columnDefinition="TIMESTAMP")
	Timestamp endTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	Users users;

	@Override
	public String toString() {
		return "Rooms{" +
				"roomId=" + roomId +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", users=" + users +
				'}';
	}
}
