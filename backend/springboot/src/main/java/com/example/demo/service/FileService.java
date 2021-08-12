package com.example.demo.service;

import java.util.List;

import com.example.demo.api.request.FileReq;
import com.example.demo.api.request.ShowFileReq;
import com.example.demo.entity.Presentations;
import com.example.demo.entity.RoomsPresentations;
import com.example.demo.model.response.FileRes;
import com.example.demo.model.response.FiledetailRes;

public interface FileService {
	FileReq saveFile(FileReq filereq) throws Exception;
//	Rooms_Presentations	getFile(int id);
	List<FileRes> findbyroomid(int roomId);
	List<FiledetailRes> findbygroupid(ShowFileReq filereq);
}
