package com.example.newswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
