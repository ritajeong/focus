package com.example.demo.api.controller;

import com.example.demo.api.request.UserUpdatePwdReq;
import com.example.demo.api.response.UserGetRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.request.LoginReq;
import com.example.demo.api.request.UserUpdateNameReq;
import com.example.demo.api.response.BaseResponseBody;
import com.example.demo.api.response.UserRes;
import com.example.demo.api.service.UserService;
import com.example.demo.db.entity.Users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) Users registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Users user = userService.createUser(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "login.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserRes> login(
			@RequestBody @ApiParam(value="로그인", required = true) LoginReq loginInfo) {
		Users user = userService.getUserByEmail(loginInfo.getEmail());
		UserRes res = new UserRes();
		res.setEmail(user.getEmail());
		res.setName(user.getName());
		res.setUser_id(user.getUserId());
		if(user==null || !user.getPassword().equals(loginInfo.getPassword()))
			return new ResponseEntity<UserRes>(HttpStatus.NOT_FOUND);
	  System.out.println(user.getEmail());
	      return new ResponseEntity<UserRes>(res,HttpStatus.OK);
	      
	   }
	@PutMapping("/update/name")
	@ApiOperation(value = "이름 변경")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> updateName(
			@RequestBody @ApiParam(value="수정", required = true) UserUpdateNameReq updateInfo) {
		Users user=userService.getUserByuserId(updateInfo.getUser_id());
		if(user==null){
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "update Name fail: 사용자가 없습니다."));
		}
		user.setName(updateInfo.getName());
		userService.updatePassword(user);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	   }
	@PutMapping("/update/password")
	@ApiOperation(value = "비밀번호 변경")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> updatePassword(
			@RequestBody @ApiParam(value="수정", required = true) UserUpdatePwdReq updateInfo) {
		Users user=userService.getUserByuserId(updateInfo.getUser_id());
		if(user==null){
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "update password fail: 사용자가 없습니다."));
		}
		user.setPassword(updateInfo.getPassword());
		userService.updatePassword(user);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "사용자삭제") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable("roomId") int userId) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Users user=userService.getUserByuserId(userId);
		userService.deleteRoom(user);
		
		if(user == null){
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
	}
	
	
	@GetMapping("/add/{email}")
	@ApiOperation(value = "참가자 추가", notes = "존재하는 회원 확인용")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> checkUser(@PathVariable("email") String email){

		Users user = userService.getUserByEmail(email);

		if(user != null){
			return ResponseEntity.status(201).body(BaseResponseBody.of(201, "존재하는 이메일 입니다."));
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 이메일 입니다."));
	}
	
	@GetMapping("/check/{email}")
	@ApiOperation(value = "회원가입할때 이메일 체크", notes = "존재하는 회원 확인용")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> addUser(@PathVariable("email") String email){

		Users user = userService.getUserByEmail(email);

		if(user != null){
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "존재하는 이메일 입니다."));
		}
		return ResponseEntity.status(201).body(BaseResponseBody.of(201, "존재하지 않는 이메일 입니다."));
	}

	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "이메일로 검색된 사용자 정보 가져오기")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 409, message = "이미 존재하는 유저"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<List<UserGetRes>> getUserByKeyword(@PathVariable("keyword") String keyword) {

		List<UserGetRes> userGetRes=userService.getUserByKeyword(keyword);
		return new ResponseEntity<List<UserGetRes>>(userGetRes,HttpStatus.OK);
	}

}
