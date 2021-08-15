package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateRequest")
public class UserUpdatePwdReq {
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	String email;
	@ApiModelProperty
	String currentPassword;
	@ApiModelProperty
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
