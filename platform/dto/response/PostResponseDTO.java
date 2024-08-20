package com.example.temel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {

    private final String username;
    private final String postTitle;
    private final String postContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

}
