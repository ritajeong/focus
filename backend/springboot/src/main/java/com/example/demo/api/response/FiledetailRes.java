package com.example.demo.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponseBody")
public class FiledetailRes {
	@ApiModelProperty
	String directory;
	@ApiModelProperty
	String original;
	public FiledetailRes(String directory, String original) {
		super();
		this.directory = directory;
		this.original = original;
	}
	
	
}
