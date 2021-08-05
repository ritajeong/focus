package com.ssafy.db.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Participants;

@Repository
public interface PartRepository extends JpaRepository<Participants, Integer>{
	@Transactional
	void deleteAllByRooms_RoomId(int roomId);
}
