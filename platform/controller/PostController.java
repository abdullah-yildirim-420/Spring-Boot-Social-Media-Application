package com.example.temel.controller;

import com.example.temel.dto.PostRequestDTO;
import com.example.temel.dto.PostResponseDTO;
import com.example.temel.dto.UpdateContentRequestDTO;
import com.example.temel.dto.UpdateTitleRequestDTO;
import com.example.temel.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO postRequestDTO){
        PostResponseDTO postResponseDTO = postService.create(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDTO> get(@PathVariable Long postId){
        PostResponseDTO postResponseDTO = postService.get(postId);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/person/{personId}/posts")
    public ResponseEntity<List<PostResponseDTO>> getAll(@PathVariable Long personId){
        List<PostResponseDTO> postList = postService.getAll(personId);
        return ResponseEntity.ok(postList);
    }

    @PatchMapping("/posts/{postId}/title")
    public ResponseEntity<PostResponseDTO> updateTitle(@PathVariable Long postId,@Valid @RequestBody UpdateTitleRequestDTO updateTitleRequestDTO){
        PostResponseDTO postResponseDTO = postService.updateTitle(postId, updateTitleRequestDTO);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PatchMapping("/posts/{postId}/content")
    public ResponseEntity<PostResponseDTO> updateContent(@PathVariable Long postId,@Valid @RequestBody  UpdateContentRequestDTO updateContentRequestDTO){
        PostResponseDTO postResponseDTO = postService.updateContent(postId, updateContentRequestDTO);
        return ResponseEntity.ok(postResponseDTO);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }




}
