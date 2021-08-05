package com.ssafy.api.request;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomRegisterPostRequest")

public class RoomRegisterPostReq {
	@ApiModelProperty
	String name;
	@ApiModelProperty
	Timestamp startTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	List<String> person;
}
