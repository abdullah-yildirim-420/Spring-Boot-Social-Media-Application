package com.example.temel.service.impl;

import com.example.temel.dto.PostRequestDTO;
import com.example.temel.dto.PostResponseDTO;
import com.example.temel.dto.UpdateContentRequestDTO;
import com.example.temel.dto.UpdateTitleRequestDTO;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.mapper.PostMapper;
import com.example.temel.messages.ErrorMessages;
import com.example.temel.repository.PostRepository;
import com.example.temel.service.PersonService;
import com.example.temel.service.PostService;
import com.example.temel.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PersonService personService;
    private final PostMapper postMapper;


    public PostServiceImpl(PostRepository postRepository, PersonService personService, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.personService = personService;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public PostResponseDTO create(PostRequestDTO postRequestDTO) {
        Person person = personService.getEntityByUsername(postRequestDTO.getUsername());
        ValidationUtil.validateMatch(person.getPassword(),postRequestDTO.getPassword(), ErrorMessages.INVALID_PASSWORD);
        Post post = postMapper.toEntity(postRequestDTO,person);

        postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public PostResponseDTO get(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomNotFoundException("post",postId));
        return postMapper.toDto(post);
    }

    @Override
    public List<PostResponseDTO> getAll(Long personId) {
        Person person = personService.getEntity(personId);
        List<Post> posts = postRepository.findAllByPersonId(person.getId());
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponseDTO updateTitle(Long postId, UpdateTitleRequestDTO updateTitleRequestDTO) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomNotFoundException("post",postId));
        Person person = personService.getEntityByUsername(updateTitleRequestDTO.getUsername());

        ValidationUtil.validateExistence(person.getId(),post.getPerson().getId(),ErrorMessages.ID_MISMATCH);
        ValidationUtil.validateMatch(person.getUsername(), updateTitleRequestDTO.getUsername(),ErrorMessages.INVALID_USERNAME);
        ValidationUtil.validateMatch(person.getPassword(), updateTitleRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        post.setPostTitle(updateTitleRequestDTO.getTitle());
        postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostResponseDTO updateContent(Long postId, UpdateContentRequestDTO updateContentRequestDTO) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomNotFoundException("post",postId));
        Person person = personService.getEntityByUsername(updateContentRequestDTO.getUsername());

        ValidationUtil.validateExistence(person.getId(),post.getPerson().getId(),ErrorMessages.ID_MISMATCH);
        ValidationUtil.validateMatch(person.getUsername(), updateContentRequestDTO.getUsername(),ErrorMessages.INVALID_USERNAME);
        ValidationUtil.validateMatch(person.getPassword(), updateContentRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        post.setPostContent(updateContentRequestDTO.getContent());
        postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    public Post getEntity(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()->new CustomNotFoundException("post",postId));
    }

    @Override
    public void delete(Long postId) {
        postRepository.findById(postId).orElseThrow(()->new CustomNotFoundException("post",postId));
        postRepository.deleteById(postId);
    }



}
