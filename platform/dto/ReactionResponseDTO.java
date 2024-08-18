package com.example.temel.dto;


import com.example.temel.enums.ReactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReactionResponseDTO {

    private final String content;
    private final ReactionType reactionType;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


}
