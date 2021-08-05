package com.ssafy.api.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.RoomRegisterPostReq;
import com.ssafy.api.request.RoomUpdatePostReq;
import com.ssafy.api.response.RoomGetRes;
import com.ssafy.db.entity.Code;
import com.ssafy.db.entity.Group_Code;
import com.ssafy.db.entity.Participants;
import com.ssafy.db.entity.Rooms;
import com.ssafy.db.repository.PartRepository;
import com.ssafy.db.repository.RoomRepository;
import com.ssafy.db.repository.UserRepository;

@Service("roomSeRoomRegisterPostReqrvice")

public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PartRepository parRepository;

	@Override
	public RoomRegisterPostReq createRoom(RoomRegisterPostReq room) {
		Rooms ro = new Rooms();
		ro.setName(room.getName());
		if(room.getStartTime()==null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			ro.setStartTime(timestamp);
			ro.setEndTime(timestamp);
		}
		ro.setUsers(userRepository.findByUserId(room.getUser_id()));
		roomRepository.save(ro);
		Rooms inroom = roomRepository.findByRoomId(ro.getRoomId());
		saveParticipants(room.getPerson(),inroom);
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
			roomres.add(new RoomGetRes(r.getName(), r.getStartTime(), r.getUsers().getUserId(), r.getRoomId()));
		}
		return roomres;
	}

	@Override
	public RoomUpdatePostReq updateRoom(RoomUpdatePostReq room) {
		Rooms upro = getRoom(room.getRoom_id());
		upro.setStartTime(room.getStartTime());
		upro.setName(room.getName());
		parRepository.deleteAllByRooms_RoomId(room.getRoom_id());
		saveParticipants(room.getPerson(), upro);
		roomRepository.save(upro);
		return room;
	}

	
}
