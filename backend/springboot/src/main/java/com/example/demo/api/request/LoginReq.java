package com.example.demo.api.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LoginRegRequest")
public class LoginReq {
	@ApiModelProperty
	String email;
	@ApiModelProperty
	String password;

}
