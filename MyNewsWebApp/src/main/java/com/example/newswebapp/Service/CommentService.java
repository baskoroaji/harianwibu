package com.example.newswebapp.Service;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.newswebapp.Mapper.CommentMapper;
import com.example.newswebapp.Model.Comment;
import com.example.newswebapp.Model.Post;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.dto.CommentRequest;
import com.example.newswebapp.repository.CommentRepository;
import com.example.newswebapp.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    

    public Integer save(CommentRequest request, Authentication connectedUser, Long postId) {
        Post post = postRepository.findById(postId)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Post not found"));
        User user = ((User) connectedUser.getPrincipal());
        Comment comment = commentMapper.toComment(request);
        comment.setUser(user);
        return commentRepository.save(comment).getId();
}
}
