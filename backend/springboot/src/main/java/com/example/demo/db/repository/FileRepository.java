package com.example.demo.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.db.entity.Presentations;

public interface FileRepository extends JpaRepository<Presentations, Integer>{
}
