package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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

}
