package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInfoRepository extends JpaRepository<Information,Long> {
    Optional<Information> findByType(String type);
}
