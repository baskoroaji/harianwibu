package com.example.newswebapp.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.newswebapp.Common.PageResponse;
import com.example.newswebapp.Service.PostService;
import com.example.newswebapp.dto.PostRequest;
import com.example.newswebapp.dto.PostResponse;

import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<PageResponse<PostResponse>> getAllPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
        ) {
    PageResponse<PostResponse> response = service.findAllPosts(page, size);
    return ResponseEntity.ok(response);
}
    
    @PatchMapping("/edit/{post-id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable("post-id") @RequestBody Long postId, Authentication connectedUser, PostRequest request){
        return ResponseEntity.ok(service.edit(connectedUser, postId, request));
    }

    @PostMapping(value = "/cover/{post-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@PathVariable("post-id") Long postId,
        @Parameter()@RequestPart("file") MultipartFile file, Authentication connectedUser) {
        
       service.uploadImage(file, connectedUser, postId);
       return ResponseEntity.accepted().build();
    }
    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") Long postId, Authentication connectedUser) {
        service.deletePost(postId, connectedUser);
        return ResponseEntity.accepted().build();
    }
    
}
