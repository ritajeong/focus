package com.example.demo.api.service;

import java.util.List;

import com.example.demo.api.request.FileReq;
import com.example.demo.api.request.ShowFileReq;
import com.example.demo.api.response.FileRes;
import com.example.demo.api.response.FiledetailRes;
import com.example.demo.db.entity.Presentations;
import com.example.demo.db.entity.RoomsPresentations;

public interface FileService {
	FileReq saveFile(FileReq filereq) throws Exception;
//	Rooms_Presentations	getFile(int id);
	List<FileRes> findbyroomid(int roomId);
	List<FiledetailRes> findbygroupid(ShowFileReq filereq);
	FileReq update(FileReq registerInfo) throws Exception;
	void delete(FileReq registerInfo);
}
