package com.blogapp.Blogapp.payload;

import lombok.Data;
@Data
public class CommentDto {
    private Long id;
    private String body;
    private String email;
    private String name;
}
