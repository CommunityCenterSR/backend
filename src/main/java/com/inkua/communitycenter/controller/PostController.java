package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Post;
import com.inkua.communitycenter.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(postService.findById(postId), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Post>> findPostsByEventDate(@RequestParam("date") Date date) {
        return new ResponseEntity<>(postService.findByEventDate(date), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Post>> findPostsByCategoryName(@RequestParam("name") String categoryName){
        return new ResponseEntity<>(postService.findByCategoryName(categoryName), HttpStatus.OK);
    }

    @GetMapping("/important")
    public ResponseEntity<List<Post>> findPostsByImportant(@RequestParam("important") byte important){
        return new ResponseEntity<>(postService.findByImportant(important), HttpStatus.OK);
    }

    // ---------------------------------------------------------

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    // ---------------------------------------------------------

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post post){
        return new ResponseEntity<>(postService.updatePost(postId, post), HttpStatus.OK);
    }

    // ---------------------------------------------------------

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable Long postId){
        return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
    }

}
