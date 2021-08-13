package com.example.demo.db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participants {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 int id;

	@ManyToOne
	@JoinColumn(name="code_id")
	Code code;

	@ManyToOne
	@JoinColumn(name="group_code")
	Group_Code groupcode;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	Users users;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	Rooms rooms;
	
}
