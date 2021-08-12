package com.example.demo.service;

import com.example.demo.model.response.ParticipantGetRes;

import java.util.List;

public interface ParticipantService {
    List<ParticipantGetRes> getParticipantByRoomId(int roomId);

}
