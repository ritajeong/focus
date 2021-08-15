package com.example.demo.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.api.request.ParticipantReq;
import com.example.demo.db.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdateReq;
import com.example.demo.api.response.RoomGetRes;
import com.example.demo.db.repository.PartRepository;
import com.example.demo.db.repository.ParticipantRegistory;
import com.example.demo.db.repository.RoomRepository;
import com.example.demo.db.repository.UserRepository;

@Service("roomSerice")

public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository roomRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PartRepository parRepository;
	
	@Autowired
	ParticipantRegistory partiRepository;


	private final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
	private final String groupCodeRole="00";

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
		Users user=userRepository.findByEmail(roomInfo.getEmail());
		room.setUsers(user);
		log.info("[createRoom] set users : {}", room);

		roomRepository.save(room);
		log.info("[createRoom] save room complete");
		//Rooms inroom = roomRepository.findByRoomId(room.getRoomId());

		saveParticipants(roomInfo.getParticipants(),room);
		log.info("[createRoom] save participants complete");
		return room;
	}

	private void saveParticipants(List<ParticipantReq> person, Rooms room) {
		Group_Code group_code = new Group_Code();
		group_code.setGroupCode(groupCodeRole);
		Participants participant;
		Code code;

		int size=person.size();
		for(int i=0;i<size;i++){
			log.info("[saveParticipants] i:{},  person: {}",i, person.get(i));
			code=new Code();
			code.setCodeId(person.get(i).getCodeId().getCodeId());

			participant = new Participants();
			participant.setCode(code);
			participant.setGroupcode(group_code);

			participant.setUsers(userRepository.findByEmail(person.get(i).getEmail()));
			participant.setRooms(room);

			parRepository.save(participant);
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
		RoomGetRes roomGetRes;
		for (Rooms r:room) {
			roomGetRes=new RoomGetRes(r.getName(), r.getDescription(), r.getStartTime().toLocalDateTime(),r.getUsers().getUserId(), r.getRoomId());
			if(r.getEndTime()==null){
				roomGetRes.setEndTime(null);
			}else{
				roomGetRes.setEndTime(r.getEndTime().toLocalDateTime());
			}
			roomres.add(roomGetRes);
		}
		return roomres;
	}

	@Override
	public Rooms updateRoom(RoomUpdateReq room) {
		Rooms updateRoomData = getRoom(room.getRoom_id());
		updateRoomData.setStartTime(Timestamp.valueOf(room.getStartTime()));
		updateRoomData.setName(room.getName());
		updateRoomData.setDescription(room.getDescription());
		parRepository.deleteAllByRooms_RoomId(room.getRoom_id());
		saveParticipants(room.getParticipants(), updateRoomData);
		roomRepository.save(updateRoomData);
		return updateRoomData;
	}

	@Override
	public List<RoomGetRes> findbyuser(int userId) {
		List<RoomGetRes> roomres = new ArrayList();
		List<Participants>pa = partiRepository.findByusers_userId(userId);
		log.info("[findbyuser] userId: {}, pa:{}", userId, pa);
		RoomGetRes roomGetRes;
		for(Participants party : pa) {
			Rooms r = party.getRooms();
			roomGetRes=new RoomGetRes(r.getName(), r.getDescription(), r.getStartTime().toLocalDateTime(), r.getUsers().getUserId(), r.getRoomId());
			log.info("[findbyuser] r:{}", r);
			if(r.getEndTime()==null){
				roomGetRes.setEndTime(null);
			}else{
				roomGetRes.setEndTime(r.getEndTime().toLocalDateTime());
			}
			roomres.add(roomGetRes);
		}
		return roomres;
	}
}
