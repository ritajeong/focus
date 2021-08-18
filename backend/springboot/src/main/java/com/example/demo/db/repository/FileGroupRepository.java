package com.example.demo.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.db.entity.RoomsPresentations;

public interface FileGroupRepository extends JpaRepository<RoomsPresentations, Integer>{
	List<RoomsPresentations> findByrooms_RoomId(int room_id);
	
	@Query(value="select group_id from rooms_presentations where room_id=?1 and user_id=?2", nativeQuery=true)
	Integer findBygroupid(int room_id,int user_id);

	@Query(value="select file_size from rooms_presentations where room_id=?1 and user_id=?2",nativeQuery=true)
	int getFileSizeByGroupId(int room_id,int user_id);

}
