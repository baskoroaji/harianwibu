package com.example.newswebapp.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.newswebapp.Common.PageResponse;
import com.example.newswebapp.Mapper.CommentMapper;
import com.example.newswebapp.Model.Comment;
import com.example.newswebapp.Model.Post;
import com.example.newswebapp.Model.User;
import com.example.newswebapp.dto.CommentRequest;
import com.example.newswebapp.dto.CommentResponse;
import com.example.newswebapp.repository.CommentRepository;
import com.example.newswebapp.repository.PostRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    

    public Long save(CommentRequest request, Authentication connectedUser) {
        Post post = postRepository.findById(request.postId())
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Post not found with ID" + request.postId()));
        User user = ((User) connectedUser.getPrincipal());
        Comment comment = commentMapper.toComment(request);
        comment.setUser(user);
        return commentRepository.save(comment).getId();
}

    public List<CommentResponse> findAllComment(Long postId) {
        Post post = postRepository.findById(postId)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Post not found"));
        return commentRepository.findByPost(post)
        .stream().map(commentMapper::toCommentResponse).toList();

}
    public void deleteComment(long commentId, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        Comment existingComment = commentRepository.findById(commentId)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment not found"));
        if(!existingComment.getUser().getUserId().equals(user.getUserId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"you are not allowed to delete this comment");
        }
        commentRepository.delete(existingComment);
    }
}
