package com.blogapp.Blogapp.service.impl;

import com.blogapp.Blogapp.entity.Comment;
import com.blogapp.Blogapp.entity.Post;
import com.blogapp.Blogapp.exception.ResourceNotFoundException;
import com.blogapp.Blogapp.payload.CommentDto;
import com.blogapp.Blogapp.repository.CommentRepository;
import com.blogapp.Blogapp.repository.PostRepository;
import com.blogapp.Blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

//    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
//        this.commentRepository = commentRepository;
//        this.postRepository = postRepository;
//    }

    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(long postId , CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "ID", postId)
        );
        Comment comment = this.maptoComment(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        return this.maptoCommentDto(save);
    }

    @Override
    public Comment maptoComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    @Override
    public CommentDto maptoCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());
        return commentDto;
    }
}
