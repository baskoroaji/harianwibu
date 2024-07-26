package com.example.newswebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.Likes;
import com.example.newswebapp.Model.Post;
import com.example.newswebapp.Model.User;

public interface LikeRepository extends JpaRepository<Likes, Integer> {
    Optional<Likes> findByUserAndPost(User user, Post post);

    


}
