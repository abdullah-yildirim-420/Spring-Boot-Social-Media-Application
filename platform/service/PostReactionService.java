package com.example.temel.service;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;

public interface PostReactionService {

    ReactionResponseDTO create(Long postId, ReactionRequestDTO reactionRequestDTO);

    ReactionResponseDTO update(Long postId, ReactionRequestDTO reactionRequestDTO);

    void deletePostReaction(Long postId, Long reactionId);
}
