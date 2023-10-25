package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.Post;

import java.util.Date;
import java.util.List;

public interface IPostService {

    List<Post> findAll();

    Post findById(Long id);

    List<Post> findByEventDate(Date date);

    List<Post> findByCategoryName(String categoryName);

    List<Post> findByImportant(byte important);

    Post createPost(Post post);

    Post updatePost(Long postId, Post post);

    Post deletePost(Long postId);

}
