package com.example.newswebapp.Mapper;

import org.springframework.stereotype.Service;

import com.example.newswebapp.Model.Comment;
import com.example.newswebapp.dto.CommentRequest;

@Service
public class CommentMapper {

    public Comment toComment(CommentRequest request){
        return Comment.builder()
        .id(request.id())
        .content(request.content())
        .build();
    }
}

