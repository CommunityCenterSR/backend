package com.example.demo.repository;

import com.example.demo.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInfoRepository extends JpaRepository<Information,Long> {
    List<Information> findByType(String type);
}
