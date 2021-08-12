package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.RoomsPresentations;
import com.example.demo.model.response.FileRes;

public interface FileGroupRepository extends JpaRepository<RoomsPresentations, Integer>{
	List<RoomsPresentations> findByrooms_RoomId(int room_id);
	
	@Query(value="select group_id from Rooms_Presentations where room_id=?1 and user_id=?2",nativeQuery=true)
	int findBygroupid(int room_id,int user_id);

}
