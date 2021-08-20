package com.example.demo.api.service;

import com.example.demo.api.request.UserRegisterReq;
import com.example.demo.api.response.UserGetRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.entity.Users;
import com.example.demo.db.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public void createUser(UserRegisterReq users) {
		Users user = new Users();
		user.setEmail(users.getEmail());
		user.setName(users.getName());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(users.getPassword());
		userRepository.save(user);
	}

	@Override
	public Users getUserByEmail(String email) {
		Users user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public void updateName(Users updateInfo) {
		userRepository.save(updateInfo);

	}
	@Override
	public void updatePassword(Users updateInfo) {
		userRepository.save(updateInfo);

	}

	@Override
	public Users getUserByuserId(int userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public void deleteUser(Users user) {
		userRepository.delete(user);
	}

	@Override
	public List<UserGetRes> getUserByKeyword(String keyword) {
		//TODO query dsl로 key워드 검색 하기
		List<Users>users=userRepository.findAll();
		List<UserGetRes> userGetRes = new ArrayList<>();
		for (Users item:users) {
			userGetRes.add(new UserGetRes(item.getUserId(), item.getEmail(), item.getName()));
		}
		return userGetRes;
	}

}
