package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterRequest")
public class UserRegisterReq {
    @ApiModelProperty(example = "사용자 아이디")
    int userId;
    @ApiModelProperty(example = "사용자 이름")
    String name;
    @ApiModelProperty(example = "사용자 이메일")
    String email;
    @ApiModelProperty(example = "사용자 비밀번호")
    String password;
}