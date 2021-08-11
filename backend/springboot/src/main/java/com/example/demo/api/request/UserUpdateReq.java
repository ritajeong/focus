package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateRequest")
public class UserUpdateReq {
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	String password;
	@ApiModelProperty
	String name;
}
