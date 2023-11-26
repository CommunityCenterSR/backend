package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Post;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Post> posts = new ArrayList<>();
        Mockito.when(postRepository.findAll()).thenReturn(posts);
        List<Post> result = postService.findAll();
        assertEquals(posts, result);
    }

    @Test
    void testFindById() {
        Long postId = 1L;
        Post post = new Post();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Post result = postService.findById(postId);
        assertEquals(post, result);
    }


    @Test
    void testDeletePost() {
        Long postId = 1L;
        Post post = new Post();
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        Post result = postService.deletePost(postId);
        assertEquals(post, result);
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testDeletePostNotFound() {
        Long postId = 1L;
        Mockito.when(postRepository.findById(postId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> postService.deletePost(postId));
        verify(postRepository, never()).deleteById(postId);
    }
}
