package com.blogapp.Blogapp.service.impl;

import com.blogapp.Blogapp.entity.Post;
import com.blogapp.Blogapp.exception.ResourceNotFoundException;
import com.blogapp.Blogapp.payload.PostDto;
import com.blogapp.Blogapp.payload.PostResponse;
import com.blogapp.Blogapp.repository.PostRepository;
import com.blogapp.Blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        return mapToDto(postEntity);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
     public List<PostDto> findByContent(String content) {
        ArrayList<Post> posts= (ArrayList<Post>) postRepo.findByContent(content);
        return posts.stream().map(x->mapToDto(x)).collect(Collectors.toList());
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "ID", id));
        return this.mapToDto(post);
    }

    public PostDto mapToDto(Post postEntity) {
        PostDto dto = new PostDto();
        dto.setId(postEntity.getId());
        dto.setTitle(postEntity.getTitle());
        dto.setDescription(postEntity.getDescription());
        dto.setContent(postEntity.getContent());
        return dto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post posts = postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post" , "ID" , id)
        );
        posts.setContent(postDto.getContent());
        posts.setTitle(postDto.getTitle());
        posts.setDescription(postDto.getDescription());
        return this.mapToDto(postRepo.save(posts));
    }

    @Override
    public void deleteById(Long id) {
        postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "ID", id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostResponse getAllPagingPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo , pageSize , sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> contents = content.stream().map(post -> this.mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageSize(posts.getSize());
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }


    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }


}
