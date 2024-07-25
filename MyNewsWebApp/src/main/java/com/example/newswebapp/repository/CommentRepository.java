package com.example.newswebapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.Comment;
import com.example.newswebapp.Model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
            List<Comment> findByPost(Post post);

}
