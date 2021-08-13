package com.example.demo.api.response;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomGetResponse")

public class RoomGetRes {
	@ApiModelProperty
	String name;
	@ApiModelProperty
	Timestamp startTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	int room_id;
	public RoomGetRes(String name, Timestamp startTime, int user_id, int room_id) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.user_id = user_id;
		this.room_id = room_id;
	}
	
	
}
