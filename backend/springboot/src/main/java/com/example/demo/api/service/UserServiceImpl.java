package com.example.demo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.request.LoginReq;
import com.example.demo.api.request.UserUpdateReq;
import com.example.demo.db.entity.Users;
import com.example.demo.db.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public Users createUser(Users users) {
		Users user = new Users();
		user.setEmail(users.getEmail());
		user.setName(users.getName());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(users.getPassword());
		return userRepository.save(user);
	}

	@Override
	public Users getUserByEmail(String email) {
		Users user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public Users update(UserUpdateReq registerInfo) {
		Users user = userRepository.findByUserId(registerInfo.getUser_id());
		user.setName(registerInfo.getName());
		user.setPassword(registerInfo.getPassword());
		userRepository.save(user);
		return user;
	}

	@Override
	public Users getUserByuserId(int userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public void deleteRoom(Users user) {
		userRepository.delete(user);
	}

	
}
