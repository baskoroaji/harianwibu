package com.example.newswebapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostRequest(
    Long id,
    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String postName,
    @NotNull(message = "100")
    @NotEmpty(message = "100")
    String content
) {

    public String getPostName() {
        return postName;
    }

    public String getContent() {
      return content;
    }

} 


