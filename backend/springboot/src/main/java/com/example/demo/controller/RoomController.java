package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.request.RoomRegisterPostReq;
import com.example.demo.api.request.RoomUpdatePostReq;
import com.example.demo.entity.Rooms;
import com.example.demo.model.response.BaseResponseBody;
import com.example.demo.model.response.RoomGetRes;
import com.example.demo.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "유저 API", tags = {"Room"})
@RestController
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;

	
	@PostMapping("/createroom")
	@ApiOperation(value = "방생성") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="방정보", required = true) RoomRegisterPostReq registerInfo) {
		System.out.println(registerInfo.getUser_id());
		RoomRegisterPostReq room = roomService.createRoom(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/updateroom/{roomId}")
	@ApiOperation(value = "방업데이트") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> update(
			@RequestBody @ApiParam(value="방업데이트", required = true) RoomUpdatePostReq registerInfo) {
		System.out.println(registerInfo.getUser_id());
		RoomUpdatePostReq room = roomService.updateRoom(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/deleteroom/{roomId}")
	@ApiOperation(value = "방삭제") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteRoom(@PathVariable("roomId") int roomId) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Rooms room = roomService.getRoom(roomId);
		roomService.deleteRoom(roomId);
		
		if(room == null){
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
	}
	@GetMapping("/")
	@ApiOperation(value = "전체 방 보기")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<RoomGetRes>> showRooms(){
		return new ResponseEntity<List<RoomGetRes>>(roomService.findAll(), HttpStatus.OK);

	}
	
	@GetMapping("/{roomId}")
	@ApiOperation(value = "방하나보기")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<RoomGetRes> showRoomone(@PathVariable("roomId") int roomId) {
		Rooms room = roomService.getRoom(roomId);
		RoomGetRes roomget = new RoomGetRes(room.getName(), room.getStartTime(), room.getUsers().getUserId(), room.getRoomId());
		return new ResponseEntity<RoomGetRes>(roomget,HttpStatus.OK);

	}
}
