package com.example.demo.api.response;

import com.example.demo.db.entity.Code;
import com.example.demo.db.entity.Group_Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ParticipantGetResponse")
public class ParticipantGetRes {
    @ApiModelProperty(name="참가자 이름")
    String name;
    @ApiModelProperty(name="참가자 이메일")
    String email;

    @ApiModelProperty(name="그룹 코드")
    Group_Code groupCode;

    @ApiModelProperty(name="역할 코드")
    Code codeId;


    public ParticipantGetRes(String name, String email, Group_Code groupCode, Code codeId) {
        this.name = name;
        this.email = email;
        this.groupCode = groupCode;
        this.codeId = codeId;
    }

}
