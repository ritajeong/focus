package com.example.demo.api.request;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileRequest")
public class FileReq {

	@ApiModelProperty
	int room_id;
	@ApiModelProperty
	int user_id;
	@ApiModelProperty
	List<MultipartFile>files;
}
