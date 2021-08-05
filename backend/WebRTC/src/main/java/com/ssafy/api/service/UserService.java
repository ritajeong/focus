package com.ssafy.api.service;

import com.ssafy.db.entity.Users;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	Users createUser(Users users);
	Users getUserByEmail(String email);

	
}
