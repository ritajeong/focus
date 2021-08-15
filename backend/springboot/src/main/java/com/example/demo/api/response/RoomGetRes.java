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
	int manager_id;
	@ApiModelProperty
	String manager_name;
	@ApiModelProperty
	int room_id;
	@ApiModelProperty(name="참가자 목록")
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
