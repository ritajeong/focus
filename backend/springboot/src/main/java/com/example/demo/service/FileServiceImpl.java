package com.example.demo.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.api.request.FileReq;
import com.example.demo.api.request.ShowFileReq;
import com.example.demo.entity.Presentations;
import com.example.demo.entity.RoomsPresentations;
import com.example.demo.model.response.FileRes;
import com.example.demo.model.response.FiledetailRes;
import com.example.demo.repository.FileGroupRepository;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.FileGroupRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	FileRepository fileRepository;
	@Autowired
	FileGroupRepository filegroupRepository;
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileGroupRepository groupRepository;
	@Override
	public FileReq saveFile(FileReq filereq) throws Exception {
		System.out.println("1234");
		RoomsPresentations pr = new RoomsPresentations();
		if(pr.getUpload_time()==null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			pr.setUpload_time(timestamp);
		}
		pr.setRooms(roomRepository.findByRoomId(filereq.getRoom_id()));
		pr.setUsers(userRepository.findByUserId(filereq.getUser_id()));
		pr.setFileSize(filereq.getFiles().size());
		groupRepository.save(pr);
		int cnt=0;
		List<String>pat=FileHandler.parseFileInfo(filereq);
        for(MultipartFile multipartFile : filereq.getFiles()) {
    		Presentations fil = new Presentations();
        	fil.setDirectory(pat.get(cnt));
        	fil.setName(Integer.toString(cnt+1));
        	fil.setOriginal(multipartFile.getOriginalFilename());
        	fil.setRoomspresentations(pr);
//    		pr.setDirectory(pat.get(cnt));
//    		pr.setName(multipartFile.getName());
    		cnt++;
    		fileRepository.save(fil);
//    		File file = new File(pat.get(cnt));
        }
		return filereq;
	}
	@Override
	public List<FileRes> findbyroomid(int roomId) {
		List<RoomsPresentations> rp = filegroupRepository.findByrooms_RoomId(roomId);
		List<FileRes> fr = new ArrayList<FileRes>();
		for (int i = 0; i < rp.size(); i++) {
			RoomsPresentations room = rp.get(i);
			FileRes file = new FileRes(room.getUsers().getUserId(), room.getFileSize());
			fr.add(file);
			
		}
		return fr;
	}
	@Override
	public List<FiledetailRes> findbygroupid(ShowFileReq filereq) {
		int group = filegroupRepository.findBygroupid(filereq.getRoom_id(), filereq.getUser_id());
		//return null;
		List<Presentations> list = fileRepository.findByroomspresentations_GroupId(group);
		List<FiledetailRes> res = new ArrayList<FiledetailRes>();
		for (int i = 0; i < list.size(); i++) {
			Presentations p = list.get(i);
			FiledetailRes fr = new FiledetailRes(p.getDirectory(), p.getOriginal());
			res.add(fr);
		}
		return res;
	}
	
//	@Override
//	public Rooms_Presentations getFile(int id) {
//		Rooms_Presentations rp = fileRepository.getById(id);
//		return rp;
//	}
	
}
