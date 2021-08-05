package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.RoomRegisterPostReq;
import com.ssafy.api.request.RoomUpdatePostReq;
import com.ssafy.api.response.RoomGetRes;
import com.ssafy.db.entity.Rooms;



public interface RoomService {
	RoomRegisterPostReq createRoom(RoomRegisterPostReq room);
	void deleteRoom(int roomId);
	Rooms getRoom(int roomId);
	//void deleteParticipants(String );
	List<RoomGetRes> findAll();
	RoomUpdatePostReq updateRoom (RoomUpdatePostReq room);

}
