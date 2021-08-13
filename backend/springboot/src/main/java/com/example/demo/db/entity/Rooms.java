package com.example.demo.db.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@Column(name="startTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Timestamp startTime;
	@Column(name="endTime")
	Timestamp endTime;
	
	 @OneToMany(mappedBy = "rooms",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Participants> participant = new ArrayList<>();
	 
	 @OneToMany(mappedBy = "rooms",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<RoomsPresentations> presentation = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	Users users;
}
