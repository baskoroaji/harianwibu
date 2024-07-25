package com.example.newswebapp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newswebapp.Model.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByOwner(Pageable pageable, Long owner);
}
