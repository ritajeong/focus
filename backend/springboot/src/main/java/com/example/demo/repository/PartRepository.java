package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Participants;
@Repository
public interface PartRepository extends JpaRepository<Participants, Integer>{
	@Transactional
	void deleteAllByRooms_RoomId(int roomId);
}
