package com.example.demo.db.entity;


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
public class Group_Code {
	@Column(name="group_code")
	@Id
	String groupCode;
	
	String groupCodeName;
}
