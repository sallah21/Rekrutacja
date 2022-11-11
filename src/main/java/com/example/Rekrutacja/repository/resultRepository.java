package com.example.Rekrutacja.repository;

import com.example.Rekrutacja.entity.result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface resultRepository extends JpaRepository<result,Integer> {
    List<result> findAll();
}
