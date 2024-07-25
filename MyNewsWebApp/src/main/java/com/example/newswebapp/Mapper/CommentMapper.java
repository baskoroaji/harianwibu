package com.example.newswebapp.Mapper;

import org.springframework.stereotype.Service;

import com.example.newswebapp.Model.Comment;
import com.example.newswebapp.Model.Post;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.dto.CommentRequest;
import com.example.newswebapp.dto.CommentResponse;

@Service
public class CommentMapper {

    public Comment toComment(CommentRequest request){
        return Comment.builder()
        .id(request.id())
        .post(Post.builder().PostId(request.postId()).build())
        .content(request.content())
        .build();
    }

    public CommentResponse toCommentResponse(Comment comment){
        return CommentResponse.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .build();
    }
}

