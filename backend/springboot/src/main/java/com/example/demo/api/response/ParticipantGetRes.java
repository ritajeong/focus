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
    @ApiModelProperty(example="참가자 이름")
    String name;
    
    @ApiModelProperty(example="참가자 이메일")
    String email;

    @ApiModelProperty(example="그룹 코드")
    Group_Code groupCode;

    @ApiModelProperty(example="역할 코드 {CodeId: 역할 아이디, CodeName: 역할 이름}")
    Code codeId;


    public ParticipantGetRes(String name, String email, Group_Code groupCode, Code codeId) {
        this.name = name;
        this.email = email;
        this.groupCode = groupCode;
        this.codeId = codeId;
    }

}
