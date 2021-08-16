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
	@ApiModelProperty
	int room_id;
	@ApiModelProperty
	String name;
	@ApiModelProperty
	String description;
	@ApiModelProperty
	LocalDateTime startTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
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
