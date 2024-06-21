package com.example.newswebapp.Mapper;

import org.springframework.stereotype.Service;

import com.example.newswebapp.Model.Post;
import com.example.newswebapp.dto.PostRequest;
import com.example.newswebapp.dto.PostResponse;

@Service
public class PostMapper {

    public Post toPost(PostRequest request){
        return Post.builder()
        .PostId(request.id())
        .PostName(request.postName())
        .content(request.content())
        .build();
    }

    public PostResponse toPostResponse(Post post){
        return PostResponse.builder()
        .postId(post.getPostId())
        .postName(post.getPostName())
        //.image(post.getImage())
        .content(post.getContent())
        .owner(post.getOwner().getUsername())
        .build();
    }
}
