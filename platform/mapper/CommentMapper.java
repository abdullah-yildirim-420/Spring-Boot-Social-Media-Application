package com.example.temel.mapper;

import com.example.temel.dto.CommentResponseDTO;
import com.example.temel.entity.Comment;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(String content, Post post, Person person){
        return new Comment(
                content,
                post,
                person
        );
    }

    public CommentResponseDTO toDto(Comment comment) {
        return new CommentResponseDTO(
                comment.getPerson().getUsername(),
                comment.getPost().getPostTitle(),
                comment.getCommentContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}