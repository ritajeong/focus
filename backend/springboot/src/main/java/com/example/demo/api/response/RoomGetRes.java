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
	@ApiModelProperty(example="방 이름")
	String name;
	@ApiModelProperty(example="방 이름")
	String description;
	@ApiModelProperty(example="방 시작 시간")
	LocalDateTime startTime;
	@ApiModelProperty(example="방 종료 시간")
	LocalDateTime endTime;
	@ApiModelProperty(example="방장 아이디")
	int manager_id;
	@ApiModelProperty(example="방장 이름")
	String manager_name;
	@ApiModelProperty(example="방 아이디")
	int room_id;
	@ApiModelProperty(example="참가자 목록")
	List<ParticipantGetRes> participants;

	public RoomGetRes(){}
	public RoomGetRes(String name, String description, LocalDateTime startTime, int manager_id, String manager_name,  int room_id, List<ParticipantGetRes> participants) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.manager_id= manager_id;
		this.manager_name = manager_name;
		this.room_id = room_id;
		this.participants = participants;
	}
	public RoomGetRes(String name, String description, LocalDateTime startTime, int manager_id, String manager_name,int room_id) {
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.manager_id= manager_id;
		this.manager_name = manager_name;
		this.room_id = room_id;
	}

	@Override
	public String toString() {
		return "RoomGetRes{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", manager_id=" + manager_id +
				", manager_name='" + manager_name + '\'' +
				", room_id=" + room_id +
				", participants=" + participants +
				'}';
	}
}
