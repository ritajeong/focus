package com.example.demo.api.response;

import java.time.LocalDateTime;
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
	String description;
	@ApiModelProperty
	LocalDateTime startTime;
	@ApiModelProperty
	LocalDateTime endTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	int room_id;
	@ApiModelProperty(name="참가자 목록")
	List<ParticipantGetRes> participants;

	public RoomGetRes(){}
	public RoomGetRes(String name, String description, LocalDateTime startTime, int user_id, int room_id, List<ParticipantGetRes> participants) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user_id = user_id;
		this.room_id = room_id;
		this.participants = participants;
	}
	public RoomGetRes(String name, String description, LocalDateTime startTime, int user_id, int room_id) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user_id = user_id;
		this.room_id = room_id;

	}

	@Override
	public String toString() {
		return "RoomGetRes{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", user_id=" + user_id +
				", room_id=" + room_id +
				", participants=" + participants +
				'}';
	}
}
