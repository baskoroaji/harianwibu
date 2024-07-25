package com.example.newswebapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
    Long id,
    @NotNull(message = "203")
    Long postId,
    @NotNull(message = "204")
    String content
) {
} 


