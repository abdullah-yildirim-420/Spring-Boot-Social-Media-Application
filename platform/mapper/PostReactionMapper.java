package com.example.temel.mapper;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import com.example.temel.entity.PostReaction;
import org.springframework.stereotype.Component;

@Component
public class PostReactionMapper {

    public PostReaction toEntity(ReactionRequestDTO reactionRequestDTO, Person person, Post post){
        return new PostReaction(
                reactionRequestDTO.getReactionType(),
                person,
                post
        );
    }

    public ReactionResponseDTO toDTO(PostReaction postReaction){
        return new ReactionResponseDTO(
                postReaction.getPost().getPostContent(),
                postReaction.getReactionType(),
                postReaction.getPerson().getUsername(),
                postReaction.getCreatedAt(),
                postReaction.getUpdatedAt()
        );
    }

}
