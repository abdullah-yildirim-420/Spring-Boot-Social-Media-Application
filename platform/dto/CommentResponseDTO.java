package com.example.temel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {

    private final String username;
    private final String postTitle;
    private final String comment;
    private final LocalDateTime createdAT;
    private final LocalDateTime updatedAt;

}

