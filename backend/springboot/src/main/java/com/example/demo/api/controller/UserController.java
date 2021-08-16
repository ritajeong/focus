package com.example.demo.api.controller;

import com.example.demo.api.request.UserRegisterReq;
import com.example.demo.api.request.UserUpdatePwdReq;
import com.example.demo.api.response.UserGetRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.request.LoginReq;
import com.example.demo.api.request.UserUpdateNameReq;
import com.example.demo.api.response.BaseResponseBody;
import com.example.demo.api.service.UserService;
import com.example.demo.db.entity.Users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

/**

유저 관련 API 요청 처리를 위한 컨트롤러 정의.
*/
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/register")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterReq registerInfo) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		userService.createUser(registerInfo);

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
	public ResponseEntity<UserGetRes> login(
			@RequestBody @ApiParam(value = "로그인", required = true) LoginReq loginInfo) {
		Users user = userService.getUserByEmail(loginInfo.getEmail());
		UserGetRes res = new UserGetRes();
		res.setEmail(user.getEmail());
		res.setName(user.getName());
		res.setUserId(user.getUserId());
		log.info("[login] loninInfo: {}, UserGetRes: {}", loginInfo, res);
		if (user == null || !user.getPassword().equals(loginInfo.getPassword())) {
			return new ResponseEntity<UserGetRes>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserGetRes>(res, HttpStatus.OK);

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
			@RequestBody @ApiParam(value = "수정", required = true) UserUpdateNameReq updateInfo) {
		Users user = userService.getUserByuserId(updateInfo.getUser_id());
		if (user == null) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "update Name fail: 사용자가 없습니다."));
		}
		user.setName(updateInfo.getName());
		userService.updatePassword(user);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@PutMapping("/update/password")
	@ApiOperation(value = "비밀번호 변경")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> updatePassword(
			@RequestBody @ApiParam(value = "수정", required = true) UserUpdatePwdReq updateInfo) {
		Users user = null;
		if (updateInfo.getEmail() != null) {//이메일로 비밀번호 변경
			user = userService.getUserByEmail(updateInfo.getEmail());
			if (user == null || !user.getEmail().equals(updateInfo.getEmail())) {
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "update password fail: 사용자가 없습니다."));
			}
		} else if (updateInfo.getCurrentPassword() != null) {//현재 비밀번호로 비밀번호 변경
			user = userService.getUserByuserId(updateInfo.getUser_id());
			if (user == null || !user.getPassword().equals(updateInfo.getCurrentPassword())) {
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "update password fail: 사용자가 없습니다."));
			}
			user.setPassword(updateInfo.getNewPassword());
		}
		log.info("[updatePassword] user: {}", user);
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
	public ResponseEntity<? extends BaseResponseBody> deleteUser(@PathVariable("userId") int userId) {

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Users user = userService.getUserByuserId(userId);
		if (user == null) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
		}
		userService.deleteUser(user);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
	}


	@GetMapping("/{email}")
	@ApiOperation(value = "이메일로 사용자 검색", notes = "계정이 존재하면 true")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserGetRes> getUserByEmail(@PathVariable("email") String email) {
		Users user = userService.getUserByEmail(email);
		UserGetRes userGetRes = null;
		if (user != null) {
			userGetRes = new UserGetRes(user.getUserId(), user.getEmail(), user.getName());
			return new ResponseEntity<UserGetRes>(userGetRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserGetRes>(userGetRes, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("check/{email}")
	@ApiOperation(value = "회원가입 - 이메일로 사용자 검색", notes = "회원가입 시 사용됨 - 계정이 없어야 true")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<? extends BaseResponseBody> checkUserByEmail(@PathVariable("email") String email) {
		Users user = userService.getUserByEmail(email);
		if (user != null) {
			log.info("사용자 계정이 존재함 user: {}", user);
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "사용자 계정이 존재합니다."));
		} else {
			log.info("사용자 계정이 없음 user: {}", user);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용자 계정이 없습니다."));
		}
	}



@GetMapping("/search/{keyword}")
@ApiOperation(value = "이메일 중 일부 키워드로 검색된 사용자 정보 가져오기")
@ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 409, message = "이미 존재하는 유저"),
        @ApiResponse(code = 500, message = "서버 오류")
})
public ResponseEntity<List<UserGetRes>> getUserByKeyword(@PathVariable("keyword") String keyword) {

    List<UserGetRes> userGetRes = userService.getUserByKeyword(keyword);
    if (userGetRes != null) {
        return new ResponseEntity<List<UserGetRes>>(userGetRes, HttpStatus.OK);
    } else {
        return new ResponseEntity<List<UserGetRes>>(userGetRes, HttpStatus.NOT_FOUND);
    }

}
}