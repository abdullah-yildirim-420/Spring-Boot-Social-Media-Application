package com.example.temel.mapper;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;
import com.example.temel.entity.Comment;
import com.example.temel.entity.CommentReaction;
import com.example.temel.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class CommentReactionMapper {
    public CommentReaction toEntity(ReactionRequestDTO reactionRequestDTO, Person person, Comment comment) {
        return new CommentReaction(
                reactionRequestDTO.getReactionType(),
                person,
                comment
        );
    }

    public ReactionResponseDTO toDTO(CommentReaction commentReaction) {
        return new ReactionResponseDTO(
                commentReaction.getComment().getCommentContent(),
                commentReaction.getReactionType(),
                commentReaction.getPerson().getUsername(),
                commentReaction.getCreatedAt(),
                commentReaction.getUpdatedAt()
        );
    }

}
