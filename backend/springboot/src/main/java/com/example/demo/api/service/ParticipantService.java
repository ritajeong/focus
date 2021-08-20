package com.example.demo.api.service;

import com.example.demo.api.response.ParticipantGetRes;

import java.util.List;

public interface ParticipantService {
    List<ParticipantGetRes> getParticipantByRoomId(int roomId);

}
