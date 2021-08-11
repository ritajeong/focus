package com.example.demo.service;

import com.example.demo.api.request.FileReq;
import com.example.demo.entity.Rooms_Presentations;

public interface FileService {
	FileReq saveFile(FileReq filereq) throws Exception;
//	Rooms_Presentations	getFile(int id);
}
