package com.example.demo.api.controller;

import java.util.List;

import com.example.demo.api.request.RoomOnLiveReq;
import com.example.demo.api.response.ParticipantGetRes;
import com.example.demo.api.service.ParticipantService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdateReq;
import com.example.demo.db.entity.Rooms;
import com.example.demo.api.response.BaseResponseBody;
import com.example.demo.api.response.RoomGetRes;
import com.example.demo.api.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "방 관련 API", tags = { "Room" })
@RestController
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;

	@Autowired
	ParticipantService participantService;

	private final Logger log = LoggerFactory.getLogger(RoomController.class);

	@PostMapping("/create")
	@ApiOperation(value = "방 생성")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm A", timezone = "Asia/Seoul")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "방정보", required = true) RoomRegisterPostReq registerInfo) {
		try {
			log.info("[register] room register info: {}", registerInfo);
			Rooms room = roomService.createRoom(registerInfo);
			log.info("[register] room : {}", room);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
	}

	@PutMapping("/update/{roomId}")
	@ApiOperation(value = "방 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> update(
			@RequestBody @ApiParam(value = "방업데이트", required = true) RoomUpdateReq roomInfo) {
		try {
			log.info("[update] roomUpdateReq: {}", roomInfo);
			Rooms room = roomService.updateRoom(roomInfo);
			log.info("[update] room: {}", room);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
	}

	@DeleteMapping("/delete/{roomId}")
	@ApiOperation(value = "방 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> deleteRoom(@PathVariable("roomId") int roomId) {
		log.info("[DeleteRoom] room {} is deleted - controller", roomId);
		Rooms room = roomService.getRoom(roomId);
		roomService.deleteRoom(roomId);

		if (room == null) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(404, "방이 없습니다."));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
	}

	@GetMapping("/")
	@ApiOperation(value = "전체 방 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<RoomGetRes>> showRooms() {

		List<RoomGetRes> rooms = roomService.findAll();
		for (RoomGetRes item : rooms) {
			item.setParticipants(participantService.getParticipantByRoomId(item.getRoom_id()));
		}
		return new ResponseEntity<List<RoomGetRes>>(rooms, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	@ApiOperation(value = "사용자 아이디가 참가자로 포함된 전체 방 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<RoomGetRes>> showRoomsbyuser(@PathVariable("userId") int userId) {

		List<RoomGetRes> rooms = roomService.findbyuser(userId);
		for (RoomGetRes item : rooms) {
			item.setParticipants(participantService.getParticipantByRoomId(item.getRoom_id()));
		}
		return new ResponseEntity<List<RoomGetRes>>(rooms, HttpStatus.OK);
	}

	@GetMapping("/onlive/{roomId}")
	@ApiOperation(value = "방이 현재 활동중인지 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> isOnLive(@PathVariable("roomId") int roomId) {
		try {
			Rooms room = roomService.getRoom(roomId);
			if (room.getOnLive()) {
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "방이 활동중입니다."));
			} else {
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "방이 활동중이지 않습니다."));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Fail"));

		}
	}

	@PutMapping("/onlive")
	@ApiOperation(value = "방의 활동 상태를 true/false로 변경하기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> setOnLive(
			@RequestBody @ApiParam(value = "방의 onLive 상태 변경", required = true) RoomOnLiveReq roomInfo) {
		try {
			log.info("[set on live] roomInfo: {}", roomInfo);
			roomService.setRoomOnLive(roomInfo);

			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "방 onLive 상태가 변경되었습니다."));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Fail"));

		}
	}

	@GetMapping("/{roomId}")
	@ApiOperation(value = "방 하나의 정보 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<RoomGetRes> showRoomone(@PathVariable("roomId") int roomId) {
		Rooms room = roomService.getRoom(roomId);
		List<ParticipantGetRes> participants = participantService.getParticipantByRoomId(roomId);
		RoomGetRes roomget = new RoomGetRes(room.getName(), room.getDescription(),
				room.getStartTime().toLocalDateTime(), room.getUsers().getUserId(), room.getUsers().getName(),
				room.getRoomId());
		if (room.getEndTime() == null) {
			roomget.setEndTime(null);
		} else {
			roomget.setEndTime(room.getEndTime().toLocalDateTime());
		}
		roomget.setParticipants(participants);

		return new ResponseEntity<RoomGetRes>(roomget, HttpStatus.OK);
	}
}