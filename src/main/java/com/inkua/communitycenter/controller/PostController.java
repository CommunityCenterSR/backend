package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Post;
import com.inkua.communitycenter.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/posts")
public class PostController {

    // ---------------------------------------------------------

    @Autowired
    private IPostService postService;

    // ---------------------------------------------------------

    @GetMapping
    public List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public Post findPostByPostId(@PathVariable Long postId){
        return postService.findById(postId);
    }

    @GetMapping("/date")
    public List<Post> findPostsByEventDate(@RequestParam("date") Date date) {
        return postService.findByEventDate(date);
    }

    @GetMapping("/category")
    public List<Post> findPostsByCategoryName(@RequestParam("name") String categoryName){
        return postService.findByCategoryName(categoryName);
    }

    @GetMapping("/important")
    public List<Post> findPostsByImportant(@RequestParam("important") byte important){
        return postService.findByImportant(important);
    }

    // ---------------------------------------------------------

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    // ---------------------------------------------------------

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post){
        return postService.updatePost(postId, post);
    }

    // ---------------------------------------------------------

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
