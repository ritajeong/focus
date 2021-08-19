package com.example.demo.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ShowFileRequest")
public class ShowFileReq {
	@ApiModelProperty
	int room_id;
	@ApiModelProperty
	int user_id;
}
