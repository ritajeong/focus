package com.example.demo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserGetResponse")
public class UserGetRes {
    @ApiModelProperty
    int userId;
    @ApiModelProperty
    String email;
    @ApiModelProperty
    String name;

    public UserGetRes() {
    }

    public UserGetRes(int userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserGetRes{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
