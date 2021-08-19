package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateRequest")
public class UserUpdatePwdReq {
	@ApiModelProperty(example = "사용자 아이디")
	int user_id;
	@ApiModelProperty(example = "사용자 이메일")
	String email;
	@ApiModelProperty(example = "현재 비밀번호")
	String currentPassword;
	@ApiModelProperty(example = "새 비밀번호")
	String newPassword;

	@Override
	public String toString() {
		return "UserUpdatePwdReq{" +
				"user_id=" + user_id +
				", email='" + email + '\'' +
				", currentPassword='" + currentPassword + '\'' +
				", newPassword='" + newPassword + '\'' +
				'}';
	}
}
