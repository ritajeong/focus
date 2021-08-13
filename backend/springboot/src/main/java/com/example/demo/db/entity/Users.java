package com.example.demo.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
	@Column(name="user_id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	String email;
	String name;
	
	//@JsonIgnore
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 String password;
	 
	 @OneToMany(mappedBy = "users",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Participants> participant = new ArrayList<>();
	 
	 @OneToMany(mappedBy = "users",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Rooms> rooms = new ArrayList<>();
	 
	 @OneToMany(mappedBy = "users",cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<RoomsPresentations> presentation = new ArrayList<>();

}
