package com.example.newswebapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newswebapp.Common.PageResponse;
import com.example.newswebapp.Service.PostService;
import com.example.newswebapp.dto.PostRequest;
import com.example.newswebapp.dto.PostResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostContoller {

    private final PostService service;
    @PostMapping
    public ResponseEntity<Long> savePost(@RequestBody @Valid PostRequest request, Authentication connectedUser) {
        
        return ResponseEntity.ok(service.save(request, connectedUser));
    }
    
    @GetMapping("/{post-id}")
    public ResponseEntity<PostResponse> viewPostById(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(service.findById(postId));
    }
    @GetMapping
    public ResponseEntity<PageResponse<PostResponse>> viewAllPost(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size,
        Authentication connectedUser
        ) {
        return ResponseEntity.ok(service.findAllPost(page, size, connectedUser));
    }
    
    @PatchMapping("/edit/{post-id}")
    public ResponseEntity<Long> editPost(@PathVariable("post-id") Long postId, Authentication connectedUser){
        return ResponseEntity.ok(service.edit(connectedUser, postId));
    }


}
