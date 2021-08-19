
package com.example.demo.api.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.api.request.FileReq;
import com.example.demo.api.request.ShowFileReq;
import com.example.demo.api.response.FileRes;
import com.example.demo.api.response.FiledetailRes;

public interface FileService {
	FileReq saveFile(FileReq filereq) throws Exception;

	// Rooms_Presentations getFile(int id);
	List<FileRes> findbyroomid(int roomId);

	List<FiledetailRes> findbygroupid(ShowFileReq filereq);

	byte[] getImage(int roomId, int userId, int currentPage) throws IOException;

	List<String> getAllImages(int roomId, int userId) throws IOException;

	FileReq update(FileReq registerInfo) throws Exception;

	void deletefile(FileReq registerInfo);

	void delete(ShowFileReq registerInfo);
}