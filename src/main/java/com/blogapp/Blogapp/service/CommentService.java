package com.blogapp.Blogapp.service;

import com.blogapp.Blogapp.entity.Comment;
import com.blogapp.Blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId , CommentDto commentDto);
    public Comment maptoComment(CommentDto commentDto);
    public  CommentDto maptoCommentDto(Comment comment);
    List<CommentDto> getCommentsByPostId(long postId);
}
