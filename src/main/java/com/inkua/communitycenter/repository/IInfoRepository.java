package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInfoRepository extends JpaRepository<Information,Long> {
    List<Information> findByType(String type);
}
