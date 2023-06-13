package com.blogapp.Blogapp.service;

import com.blogapp.Blogapp.entity.Post;
import com.blogapp.Blogapp.payload.PostDto;
import com.blogapp.Blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    public List<PostDto> findByContent(String content);

    public PostDto findById(Long id);

    PostDto mapToDto(Post post);

    PostDto updatePost(PostDto postDto, Long id);

    void deleteById(Long id);

    PostResponse getAllPagingPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
