package com.example.demo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.controller.RoomController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdatePostReq;
import com.example.demo.entity.Code;
import com.example.demo.entity.Group_Code;
import com.example.demo.entity.Participants;
import com.example.demo.entity.Rooms;
import com.example.demo.model.response.RoomGetRes;
import com.example.demo.repository.PartRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;

@Service("roomSeRoomRegisterPostReqrvice")

public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PartRepository parRepository;

	private final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);

	@Override
	public Rooms createRoom(RoomRegisterPostReq roomInfo) {
		log.info("[createRoom] room post req: {}", roomInfo);
		Rooms room = new Rooms();
		room.setName(roomInfo.getName());
		if(roomInfo.getStartTime()==null) {
			LocalDateTime datetime = LocalDateTime.now();
			room.setStartTime(Timestamp.valueOf(datetime));
		}else{
			room.setStartTime(Timestamp.valueOf(roomInfo.getStartTime()));
		}
		room.setDescription(roomInfo.getDescription());
		room.setUsers(userRepository.findByUserId(roomInfo.getUser_id()));
		log.info("[createRoom] set users : {}", room);

		roomRepository.save(room);
		log.info("[createRoom] save room complete");
		//Rooms inroom = roomRepository.findByRoomId(room.getRoomId());

		saveParticipants(roomInfo.getPerson(),room);
		log.info("[createRoom] save participants complete");
		return room;
	}

	private void saveParticipants(List<String> ro, Rooms inroom) {
		Group_Code co = new Group_Code();		
		co.setGroupCode("00");
		Participants head = new Participants();
		Code he = new Code();
		he.setCodeId("001");
		head.setUsers(userRepository.findByUserId(inroom.getUsers().getUserId()));
		head.setCode(he);
		head.setGroupcode(co);
		head.setRooms(inroom);
		parRepository.save(head);

		for (int i = 0; i < ro.size(); i++) {
			String personcode=ro.get(i);
			String person=personcode.substring(0,personcode.length()-3);
			String code = personcode.substring(personcode.length()-3,personcode.length());
			System.out.println(code);
			Code gc = new Code();
			gc.setCodeId(code);
			Participants par = new Participants();
			par.setCode(gc);
			System.out.println(par.getCode());
			par.setUsers(userRepository.findByEmail(person));
			par.setGroupcode(co);
			par.setRooms(inroom);
			parRepository.save(par);
		}
	}

	@Override //방삭제
	public void deleteRoom(int roomId) {
		parRepository.deleteAllByRooms_RoomId(roomId);
		roomRepository.deleteAllByRoomId(roomId);
	}

	@Override
	public Rooms getRoom(int roomId) {
		Rooms room=roomRepository.getById(roomId);
		return room;
	}

	@Override
	public List<RoomGetRes> findAll() {
		List<Rooms>room=roomRepository.findAll();
		List<RoomGetRes> roomres = new ArrayList();
		
		for (Rooms r:room) {
			roomres.add(new RoomGetRes(r.getName(), r.getDescription(), r.getStartTime().toLocalDateTime(), r.getUsers().getUserId(), r.getRoomId()));
		}
		return roomres;
	}

	@Override
	public Rooms updateRoom(RoomUpdatePostReq room) {
		Rooms upro = getRoom(room.getRoom_id());
		upro.setStartTime(Timestamp.valueOf(room.getStartTime()));
		upro.setName(room.getName());
		parRepository.deleteAllByRooms_RoomId(room.getRoom_id());
		saveParticipants(room.getPerson(), upro);
		roomRepository.save(upro);
		return upro;
	}

	
}
