package com.example.demo.db.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
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
public class RoomsPresentations {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="group_id")
	int groupId;
	@Column(name="file_size")
	int fileSize;
	 
	@Column(name="upload_time", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp upload_time;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	Users users;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	Rooms rooms;
}
