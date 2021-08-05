package com.ssafy.db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rooms_Presentations {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 int id;
	 String name;
	 String directory;
	Timestamp upload_time;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	Users users;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	Rooms rooms;
}
