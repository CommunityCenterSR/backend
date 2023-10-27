package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVolunteerRepository extends JpaRepository<Volunteer, Long> {
}
