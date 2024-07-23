package com.example.newswebapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
    @NotNull(message = "202")
    Integer id,
    @NotNull(message = "203")
    @NotBlank(message = "203")
    String content

) {
} 


