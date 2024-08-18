package com.example.temel.service;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;

public interface CommentReactionService {

    ReactionResponseDTO create(Long commentId, ReactionRequestDTO reactionRequestDTO);

    ReactionResponseDTO update(Long commentId, ReactionRequestDTO reactionRequestDTO);

    void deleteCommentReaction(Long commentId, Long reactionId);
}

