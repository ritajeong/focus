package com.example.demo.api.request;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
	String description;
	@ApiModelProperty
	LocalDateTime startTime;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	List<String> person;

	@Override
	public String toString() {
		return "RoomRegisterPostReq{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", user_id=" + user_id +
				", person=" + person +
				'}';
	}
}
