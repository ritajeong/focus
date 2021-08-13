package com.example.demo.api.response;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomGetResponse")
public class UserRes {
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	String email;
	@ApiModelProperty
	String name;

}
