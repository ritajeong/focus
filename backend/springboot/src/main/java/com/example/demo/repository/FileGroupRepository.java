package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Rooms_Presentations;
import com.example.demo.model.response.FileRes;

public interface FileGroupRepository extends JpaRepository<Rooms_Presentations, Integer>{
	List<Rooms_Presentations> findByrooms_RoomId(int room_id);

}
