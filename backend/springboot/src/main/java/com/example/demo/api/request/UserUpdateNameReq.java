package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdateRequest")
public class UserUpdateNameReq {
	@ApiModelProperty(example = "사용자 아이디")
	int user_id;
	@ApiModelProperty(example = "사용자 이름")
	String name;
}
