package com.example.demo.api.request;

import java.util.List;

import com.example.demo.entity.Users;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ParticipantsRegRequest")
public class ParticipantRegisterReq {
	@ApiModelProperty
	Users user;
	@ApiModelProperty(name="방 아이디")
	int roon_id;
	@ApiModelProperty(name="그룹 코드")
	String group_code;
	@ApiModelProperty(name="역할 코드", example = "001")
	String code;

	@Override
	public String toString() {
		return "ParticipantRegisterReq{" +
				"user=" + user +
				", roon_id=" + roon_id +
				", group_code='" + group_code + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
