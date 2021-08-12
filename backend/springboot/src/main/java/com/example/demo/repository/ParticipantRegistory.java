package com.example.demo.repository;

import com.example.demo.entity.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ParticipantRegistory extends JpaRepository<Participants, Integer> {
    @Transactional
    List<Participants> findByrooms_roomId(int room_id);;

}
