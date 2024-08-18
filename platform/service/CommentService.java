package com.example.temel.service;

import com.example.temel.dto.CommentRequestDTO;
import com.example.temel.dto.CommentResponseDTO;
import com.example.temel.entity.Comment;

public interface CommentService {
    CommentResponseDTO create(Long postId, CommentRequestDTO commentRequestDTO);

    CommentResponseDTO get(Long commentId);

    CommentResponseDTO update(Long commentId, CommentRequestDTO commentRequestDTO);


    Comment getEntity(Long commentId);

    void delete(Long commentId);
}
