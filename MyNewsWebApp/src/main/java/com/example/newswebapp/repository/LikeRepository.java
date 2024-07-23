package com.example.newswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

    


}
