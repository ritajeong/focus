package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Rooms_Presentations;

public interface FileRepository extends JpaRepository<Rooms_Presentations, Integer>{
}
