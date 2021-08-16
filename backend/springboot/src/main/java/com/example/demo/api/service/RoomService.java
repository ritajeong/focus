package com.example.demo.api.service;

import java.util.List;

import com.example.demo.api.request.RoomOnLiveReq;
import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdateReq;
import com.example.demo.db.entity.Rooms;
import com.example.demo.api.response.RoomGetRes;


public interface RoomService {
	Rooms createRoom(RoomRegisterPostReq room);
	void deleteRoom(int roomId);
	Rooms getRoom(int roomId);
	//void deleteParticipants(String );
	List<RoomGetRes> findAll();
	Rooms updateRoom (RoomUpdateReq room);
	List<RoomGetRes> findbyuser(int userId);
	void setRoomOnLive(RoomOnLiveReq roomInfo);
}
