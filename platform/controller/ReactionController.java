package com.example.temel.controller;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;
import com.example.temel.service.CommentReactionService;
import com.example.temel.service.PostReactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReactionController {

    private final PostReactionService postReactionService;
    private final CommentReactionService commentReactionService;

    public ReactionController(PostReactionService postReactionService, CommentReactionService commentReactionService) {
        this.postReactionService = postReactionService;
        this.commentReactionService = commentReactionService;
    }

    @PostMapping("/posts/{postId}/reactions")
    public ResponseEntity<ReactionResponseDTO> createPostReaction(@PathVariable Long postId, @Valid @RequestBody ReactionRequestDTO reactionRequestDTO){
        ReactionResponseDTO postReactionResponseDTO = postReactionService.create(postId, reactionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postReactionResponseDTO);
    }

    @PatchMapping("/posts/{postId}/reactions")
    public ResponseEntity<ReactionResponseDTO> updatePostReaction(@PathVariable Long postId, @Valid @RequestBody ReactionRequestDTO reactionRequestDTO){
        ReactionResponseDTO postReactionResponseDTO = postReactionService.update(postId, reactionRequestDTO);
        return ResponseEntity.ok(postReactionResponseDTO);
    }

    @DeleteMapping("/posts/{postId}/reactions/{reactionId}")
    public ResponseEntity<Void> deletePostReaction(@PathVariable Long postId,@PathVariable Long reactionId){
        postReactionService.deletePostReaction(postId,reactionId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/comments/{commentId}/reactions")
    public ResponseEntity<ReactionResponseDTO> createCommentReaction(@PathVariable Long commentId, @Valid @RequestBody ReactionRequestDTO reactionRequestDTO){
        ReactionResponseDTO commentReactionResponseDTO = commentReactionService.create(commentId, reactionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentReactionResponseDTO);
    }

    @PatchMapping("/comments/{commentId}/reactions")
    public ResponseEntity<ReactionResponseDTO> updateCommentReaction(@PathVariable Long commentId, @Valid @RequestBody ReactionRequestDTO reactionRequestDTO){
        ReactionResponseDTO commentReactionResponseDTO = commentReactionService.update(commentId, reactionRequestDTO);
        return ResponseEntity.ok(commentReactionResponseDTO);
    }

    @DeleteMapping("/comments/{commentId}/reactions/{reactionId}")
    public ResponseEntity<Void> deleteCommentReaction(@PathVariable Long commentId,@PathVariable Long reactionId){
        commentReactionService.deleteCommentReaction(commentId,reactionId);
        return ResponseEntity.noContent().build();
    }

}
