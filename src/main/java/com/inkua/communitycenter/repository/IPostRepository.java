package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.eventDate = :date")
    List<Post> findByEventDate(@Param("date") Date date);

    List<Post> findByCategoryName(String category);

    List<Post> findByImportant(byte important);

}
