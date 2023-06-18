package com.blogapp.Blogapp.controller;

import com.blogapp.Blogapp.payload.CommentDto;
import com.blogapp.Blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    public CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long id, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(value = "postId") Long id) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(id), HttpStatus.FOUND);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "id") long id,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateCommentByPostId(postId, id, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                           @PathVariable(value = "id") long commentId){
            commentService.deleteCommentByPostId(postId , commentId);
            return new ResponseEntity<>("Comment is Deleted" , HttpStatus.OK);
    }
}
