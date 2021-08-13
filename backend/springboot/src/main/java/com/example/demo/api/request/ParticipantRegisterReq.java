package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ParticipantsRegRequest")
public class ParticipantRegisterReq {
	@ApiModelProperty(name="참가자 이름", example = "관리자")
	String name;
	@ApiModelProperty(name="참가자 이메일", example = "master@master.com")
	String email;

//	@ApiModelProperty(name="그룹 코드")
//	String group_code;
	@ApiModelProperty(name="역할 코드", example = "001")
	String codeId;
	@ApiModelProperty(name="역할 코드 이름", example = "방장")
	String codeName;

	@Override
	public String toString() {
		return "ParticipantRegisterReq{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", codeId='" + codeId + '\'' +
				", codeName='" + codeName + '\'' +
				'}';
	}
}
