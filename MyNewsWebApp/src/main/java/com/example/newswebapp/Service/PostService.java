package com.example.newswebapp.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.newswebapp.Common.PageResponse;
import com.example.newswebapp.File.FileService;
import com.example.newswebapp.Mapper.PostMapper;
import com.example.newswebapp.Model.Post;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.dto.PostRequest;
import com.example.newswebapp.dto.PostResponse;
import com.example.newswebapp.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FileService fileStorageService;
    public Long save(PostRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Post post = postMapper.toPost(request);
        post.setOwner(user);
        return postRepository.save(post).getPostId();
}
    public PostResponse findById(Long postId){
        return postRepository.findById(postId)
        .map(postMapper::toPostResponse)
        .orElseThrow(() -> new EntityNotFoundException("No Post found with id: " + postId));
    }
    public PageResponse<PostResponse> findAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findAll(pageable);
        
        List<PostResponse> postResponses = posts.stream()
            .map(postMapper::toPostResponse)
            .toList();
    
        return new PageResponse<>(
            postResponses,
            posts.getNumber(),
            posts.getSize(),
            posts.getTotalElements(),
            posts.getTotalPages(),
            posts.isFirst(),
            posts.isLast()
        );
    }
    public PostResponse edit(Authentication connectedUser, Long postId, PostRequest request) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        User user = ((User) connectedUser.getPrincipal());
        if (!Objects.equals(existingPost.getOwner().getUserId(), user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author of this post");
        }
        existingPost.setPostName(request.postName());
        existingPost.setContent(request.content());
        return postMapper.toPostResponse(postRepository.save(existingPost));
 }

    public void deletePost(Long postId, Authentication connectedUser) {
    User user = ((User) connectedUser.getPrincipal());
    Post existingPost = postRepository.findById(postId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

    if (!existingPost.getOwner().getUserId().equals(user.getUserId())) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the author of this post");
    }

    postRepository.delete(existingPost);
}
public void uploadImage(MultipartFile file, Authentication connectedUser, Long postId) {
 
    Post existingPost = postRepository.findById(postId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    User user = ((User) connectedUser.getPrincipal());
    var postImage = fileStorageService.saveFile(file, postId, user.getUserId());
    existingPost.setImage(postImage);
    postRepository.save(existingPost);
}
}