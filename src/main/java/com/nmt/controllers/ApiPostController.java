/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.controllers;

import com.nmt.model.Comment;
import com.nmt.model.Post;
import com.nmt.service.CommentService;
import com.nmt.service.PostService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author acer
 */
@RestController
@RequestMapping("/api")
public class ApiPostController {
    @Autowired
    private PostService postService;
    
     @Autowired
    private CommentService commentService;
            
    @GetMapping("/posts/")
    @CrossOrigin
    public ResponseEntity<List<Post>> list(Map<String, String> params) {
        List<Post> list = postService.getPosts(params);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping(path = "/posts/{postId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Post> getPostById(@PathVariable(value = "postId") int postId) {
        Post post = postService.getPostByid(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @GetMapping(path = "/posts/{postId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Comment>> getSubjectByLecturerId(@PathVariable(value = "postId") int postId) {
        List<Comment> list = commentService.getCommentByPostId(postId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @PostMapping(path="/add-post/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post p = this.postService.addPost(post);
        
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
    
    @PostMapping(path="/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment c = this.commentService.addComment(comment);
        
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
