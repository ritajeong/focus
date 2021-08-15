package com.example.demo.api.response;

import java.sql.Timestamp;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("ParticipantsResponse")
public class ParticipantsRes {
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	String group_code;
	@ApiModelProperty
	String code;
	@ApiModelProperty
	int room_id;
	
	
}	
