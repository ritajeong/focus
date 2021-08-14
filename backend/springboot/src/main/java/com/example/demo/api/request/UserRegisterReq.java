package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserRegisterRequest")
public class UserRegisterReq {
    @ApiModelProperty
    int userId;
    @ApiModelProperty
    String name;
    @ApiModelProperty
    String email;
    @ApiModelProperty
    String password;
}