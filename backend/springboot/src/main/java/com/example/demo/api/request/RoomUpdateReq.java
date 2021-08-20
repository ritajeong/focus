package com.example.demo.api.request;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomUpdatePostRequest")

public class RoomUpdateReq {
	@ApiModelProperty(example="방 아이디")
	int room_id;
	@ApiModelProperty(example="방 이름")
	String name;
	@ApiModelProperty(example="방 설명")
	String description;
	@ApiModelProperty(example="방 시작 시간")
	LocalDateTime startTime;
	@ApiModelProperty(example="방장 아이디")
	int user_id;
	@ApiModelProperty(example="참가자 목록")
	List<ParticipantReq> participants;

	@Override
	public String toString() {
		return "RoomUpdateReq{" +
				"room_id=" + room_id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", user_id=" + user_id +
				", participants=" + participants +
				'}';
	}
}
