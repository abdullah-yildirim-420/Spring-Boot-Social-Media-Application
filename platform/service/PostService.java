package com.example.temel.service;

import com.example.temel.dto.PostRequestDTO;
import com.example.temel.dto.PostResponseDTO;
import com.example.temel.dto.UpdateContentRequestDTO;
import com.example.temel.dto.UpdateTitleRequestDTO;
import com.example.temel.entity.Post;

import java.util.List;

public interface PostService {
    PostResponseDTO create(PostRequestDTO postRequestDTO);

    PostResponseDTO get(Long postId);

    List<PostResponseDTO> getAll(Long personId);


    PostResponseDTO updateTitle(Long postId, UpdateTitleRequestDTO updateTitleRequestDTO);

    PostResponseDTO updateContent(Long postId, UpdateContentRequestDTO updateContentRequestDTO);

    Post getEntity(Long id);

    void delete(Long postId);
}
