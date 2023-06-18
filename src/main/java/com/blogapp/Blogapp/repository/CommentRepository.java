package com.blogapp.Blogapp.repository;

import com.blogapp.Blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    List<Comment> findByEmail(String email);
}
