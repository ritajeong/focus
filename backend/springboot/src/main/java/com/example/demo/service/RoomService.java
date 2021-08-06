package com.example.demo.service;

import java.util.List;

import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdatePostReq;
import com.example.demo.entity.Rooms;
import com.example.demo.model.response.RoomGetRes;


public interface RoomService {
	RoomRegisterPostReq createRoom(RoomRegisterPostReq room);
	void deleteRoom(int roomId);
	Rooms getRoom(int roomId);
	//void deleteParticipants(String );
	List<RoomGetRes> findAll();
	RoomUpdatePostReq updateRoom (RoomUpdatePostReq room);

}
