package com.example.demo.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.api.request.FileReq;
import com.example.demo.entity.Rooms_Presentations;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	FileRepository fileRepository;
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public FileReq saveFile(FileReq filereq) throws Exception {
		System.out.println("1234");
		Rooms_Presentations pr = new Rooms_Presentations();
		if(pr.getUpload_time()==null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			pr.setUpload_time(timestamp);
		}
		List<String>pat=FileHandler.parseFileInfo(filereq);
		pr.setRooms(roomRepository.findByRoomId(filereq.getRoom_id()));
		pr.setUsers(userRepository.findByUserId(filereq.getUser_id()));
		int cnt=0;
        for(MultipartFile multipartFile : filereq.getFiles()) {
    		pr.setDirectory(pat.get(cnt));
    		pr.setName(multipartFile.getName());
    		cnt++;
    		fileRepository.save(pr);
//    		File file = new File(pat.get(cnt));
        }
		return filereq;
	}

	@Override
	public Rooms_Presentations getFile(int id) {
		Rooms_Presentations rp = fileRepository.getById(id);
		return rp;
	}
	
}
