package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
