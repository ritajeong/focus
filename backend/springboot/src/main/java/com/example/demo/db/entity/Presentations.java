package com.example.demo.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Presentations {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="file_id")
	 int fileId;
	 String name;
	 String original;
	 String directory;
	 
	 @ManyToOne
	@JoinColumn(name="group_id")
	 RoomsPresentations roomspresentations;
}
