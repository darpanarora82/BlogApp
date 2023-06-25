package com.blogapp.Blogapp.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotNull
    @Size(min = 2,message = "Post title should've atleast 2 characters")
    private String title;
    @NotNull
    @Size(min = 10,message = "Min size is 10 for description")
    private String description;
    @NotNull
    private String content;
}
