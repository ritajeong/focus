package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.api.request.FileReq;
import com.example.demo.model.response.BaseResponseBody;
import com.example.demo.service.FileService;

import io.swagger.annotations.ApiParam;
@CrossOrigin
@RestController
public class FileController {
	@Autowired
	private FileService fileservice;
	  @PostMapping("/board")
	    public ResponseEntity<?> create(
	    		@ModelAttribute @ApiParam(value="파일 저장", required = true) FileReq registerInfo
	    		)throws Exception {
		  	FileReq file = fileservice.saveFile(registerInfo);
			return  ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));	
	    }
}
