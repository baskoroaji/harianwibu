package com.example.newswebapp.dto;

public record LikeRequest(
    Long postId,
    Long userId
) {

}
