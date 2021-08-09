package com.example.demo.api.request;

import java.sql.Timestamp;
import java.util.List;

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
	Timestamp startTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	List<String> person;
}
