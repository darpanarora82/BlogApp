package com.blogapp.Blogapp.service;

import com.blogapp.Blogapp.entity.Comment;
import com.blogapp.Blogapp.payload.CommentDto;

public interface CommentService {
    public CommentDto createComment(long postId , CommentDto commentDto);
    public Comment maptoComment(CommentDto commentDto);
    public  CommentDto maptoCommentDto(Comment comment);
}
