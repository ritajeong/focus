package com.example.demo.api.request;

import com.example.demo.db.entity.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ParticipantsRequest")
public class ParticipantReq {
	@ApiModelProperty(name="참가자 이름", example = "관리자")
	String name;
	@ApiModelProperty(name="참가자 이메일", example = "master@master.com")
	String email;

	@ApiModelProperty(name="역할 코드 객체", example = "{001, 방장}")
	Code codeId;

	@Override
	public String toString() {
		return "ParticipantUpdateReq{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", codeId=" + codeId +
				'}';
	}
}
