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
	@ApiModelProperty(example = "참가자 이름")
	String name;
	@ApiModelProperty(example = "참가자 이메일")
	String email;

	@ApiModelProperty(example = "참가자의 역할 정보 {역할 아이디,역할 이름}")
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
