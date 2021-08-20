package com.example.demo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileResponse")
public class FileRes {
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	int file_size;

	public FileRes(int user_id, int file_size) {
		super();
		this.user_id = user_id;
		this.file_size = file_size;
	}

}
