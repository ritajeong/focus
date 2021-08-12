package com.example.demo.api.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Participants;
import com.example.demo.entity.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomRegisterPostRequest")

public class RoomRegisterPostReq {
	@ApiModelProperty(name="방 이름", example = "ssafy_room")
	String name;
	@ApiModelProperty(name="방 설명", example = "ssafy_room_description")
	String description;
	@ApiModelProperty(name="시작 시간")
	LocalDateTime startTime;
	@ApiModelProperty(name="방장 이메일", example = "master@master.com")
	String email;
	@ApiModelProperty(name="참가자 목록")
	List<ParticipantRegisterReq> participants;

	@Override
	public String toString() {
		return "RoomRegisterPostReq{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", email=" + email +
				", participants=" + participants +
				'}';
	}
}
