package com.example.demo.api.service;

import com.example.demo.api.request.UserUpdateReq;
import com.example.demo.db.entity.Users;
import com.example.demo.api.response.UserGetRes;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	Users createUser(Users users);
	Users getUserByEmail(String email);
	Users update(UserUpdateReq registerInfo);
	Users getUserByuserId(int userId);
	void deleteRoom(Users user);
	List<UserGetRes> getUserByKeyword(String keyword);
}
