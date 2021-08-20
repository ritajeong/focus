package com.example.demo.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.entity.Presentations;

@Repository
public interface FileRepository extends JpaRepository<Presentations, Integer> {
	// @Query(value="select g.user_id from Presentations p inner join
	// Rooms_Presentations g where g.room_id=?1",nativeQuery=true)
//    List<FileRes> findAllBygoupid(int roomId);
	List<Presentations> findByroomspresentations_GroupId(int groupId);

}