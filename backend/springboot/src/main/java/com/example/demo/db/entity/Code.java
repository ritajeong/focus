package com.example.demo.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Code {
	@Column(name = "code_id")
	@Id
	String codeId;

	String codeName;

	@Override
	public String toString() {
		return "Code{" + "codeId='" + codeId + '\'' + ", codeName='" + codeName + '\'' + '}';
	}
}
