package com.example.temel.controller;

import com.example.temel.dto.CommentRequestDTO;
import com.example.temel.dto.CommentResponseDTO;
import com.example.temel.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDTO> create(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO commentRequestDTO){
        CommentResponseDTO commentResponseDTO = commentService.create(postId,commentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> get(@PathVariable Long commentId){
        CommentResponseDTO commentResponseDTO = commentService.get(commentId);
        return  ResponseEntity.ok(commentResponseDTO);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable Long commentId,@Valid @RequestBody CommentRequestDTO commentRequestDTO){
        CommentResponseDTO commentResponseDTO = commentService.update(commentId,commentRequestDTO);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId){
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }









}
