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
@ApiModel("RoomUpdatePostRequest")

public class RoomUpdatePostReq {
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
	List<ParticipantRegisterReq> participants;
}
