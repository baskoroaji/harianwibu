package com.example.newswebapp.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newswebapp.Service.CommentService;
import com.example.newswebapp.dto.CommentRequest;
import com.example.newswebapp.dto.CommentResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;


    @PostMapping
    public ResponseEntity<Long> saveComment(@RequestBody @Valid CommentRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<List<CommentResponse>> getAllCommentFromPost(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(service.findAllComment(postId));
    }
    
}
