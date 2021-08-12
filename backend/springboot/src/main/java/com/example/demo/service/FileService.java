package com.example.demo.service;

import java.util.List;

import com.example.demo.api.request.FileReq;
import com.example.demo.entity.Presentations;
import com.example.demo.entity.Rooms_Presentations;
import com.example.demo.model.response.FileRes;

public interface FileService {
	FileReq saveFile(FileReq filereq) throws Exception;
//	Rooms_Presentations	getFile(int id);


	List<FileRes> findbygroupid(int roomId);
}
