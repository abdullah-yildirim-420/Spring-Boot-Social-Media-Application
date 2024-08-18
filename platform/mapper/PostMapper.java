package com.example.temel.mapper;

import com.example.temel.dto.PostRequestDTO;
import com.example.temel.dto.PostResponseDTO;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toEntity(PostRequestDTO postRequestDTO, Person person){
        return new Post(
                postRequestDTO.getPostTitle(),
                postRequestDTO.getPostContent(),
                person
        );
    }

    public PostResponseDTO toDto(Post post){
        return new PostResponseDTO(
                post.getPerson().getUsername(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }




}
