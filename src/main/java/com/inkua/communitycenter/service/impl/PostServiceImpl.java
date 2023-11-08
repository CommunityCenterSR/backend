package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Post;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.IPostRepository;
import com.inkua.communitycenter.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(()-> new NotFoundException("No se encontró un Post con id: " + id));
    }

    @Override
    public List<Post> findByEventDate(Date date) {

        List<Post> postList = postRepository.findByEventDate(date);

        if (postList.isEmpty()){
            throw new NotFoundException("No se encontró ningún evento en la fecha " + date);
        }

        return postList;
    }

    @Override
    public List<Post> findByCategoryName(String categoryName) {

        List<Post> postList = postRepository.findByCategoryName(categoryName);

        if (postList.isEmpty()){
            throw new NotFoundException("No se encontró ningún evento con la categoría " + categoryName);
        }

        return postList;
    }

    @Override
    public List<Post> findByImportant(byte important) {

        List<Post> postList = postRepository.findByImportant(important);

        if (postList.isEmpty()){
            throw new NotFoundException("No hay eventos importantes ni destacados");
        }

        return postList;
    }

    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(new Date());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post post) {

        Post oldPost = findById(postId);

        post.setId(postId);
        post.setCreatedAt(oldPost.getCreatedAt()); // Para que no se resetee la fecha de creación

        return postRepository.save(post);
    }

    @Override
    public Post deletePost(Long postId) {

        Optional<Post> postExists = postRepository.findById(postId);

        if (postExists.isEmpty())
            throw new NotFoundException("No se encontró ningún Post con ID: " + postId);

        postRepository.deleteById(postId);
        return postExists.get();
    }


}
