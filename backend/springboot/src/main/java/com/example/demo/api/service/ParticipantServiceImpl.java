package com.example.demo.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.response.ParticipantGetRes;
import com.example.demo.db.entity.Participants;
import com.example.demo.db.repository.ParticipantRepository;

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService {
	@Autowired
	ParticipantRepository participantRepository;

	@Override
	public List<ParticipantGetRes> getParticipantByRoomId(int roomId) {
		List<Participants> participants = participantRepository.findByrooms_roomId(roomId);
		List<ParticipantGetRes> participantGetReqList = new ArrayList<>();

		for (Participants item : participants) {
			participantGetReqList.add(new ParticipantGetRes(item.getUsers().getName(), item.getUsers().getEmail(),
					item.getGroupcode(), item.getCode()));
		}
		return participantGetReqList;
	}

}
