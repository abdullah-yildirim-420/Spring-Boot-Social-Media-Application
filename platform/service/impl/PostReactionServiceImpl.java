package com.example.temel.service.impl;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import com.example.temel.entity.PostReaction;
import com.example.temel.exception.CustomAlreadyExistException;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.mapper.PostReactionMapper;
import com.example.temel.messages.ErrorMessages;
import com.example.temel.repository.PostReactionRepository;
import com.example.temel.service.PersonService;
import com.example.temel.service.PostReactionService;
import com.example.temel.service.PostService;
import com.example.temel.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostReactionServiceImpl implements PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final PostService postService;
    private final PersonService personService;
    private final PostReactionMapper postReactionMapper;

    public PostReactionServiceImpl(PostReactionRepository postReactionRepository, PostService postService, PersonService personService, PostReactionMapper postReactionMapper) {
        this.postReactionRepository = postReactionRepository;
        this.postService = postService;
        this.personService = personService;
        this.postReactionMapper = postReactionMapper;
    }


    @Override
    @Transactional
    public ReactionResponseDTO create(Long postId, ReactionRequestDTO reactionRequestDTO) {
        Post post = postService.getEntity(postId);
        Person person = personService.getEntityByUsername(reactionRequestDTO.getUsername());

        ValidationUtil.validateMatch(person.getPassword(), reactionRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        postReactionRepository.findByPersonIdAndPostId(person.getId(),postId).ifPresent(reaction->{
            throw new CustomAlreadyExistException(ErrorMessages.REACTION_EXIST);
        });

        PostReaction postReaction = postReactionMapper.toEntity(reactionRequestDTO,person,post);
        postReactionRepository.save(postReaction);
        return postReactionMapper.toDTO(postReaction);
    }

    @Override
    @Transactional
    public ReactionResponseDTO update(Long postId, ReactionRequestDTO reactionRequestDTO) {
        postService.getEntity(postId);
        Person person = personService.getEntityByUsername(reactionRequestDTO.getUsername());

        ValidationUtil.validateMatch(person.getPassword(), reactionRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        PostReaction postReaction = postReactionRepository.findByPersonIdAndPostId(person.getId(),postId).orElseThrow(()->new CustomNotFoundException(ErrorMessages.REACTION_NOT_FOUND));
        postReaction.setReactionType(reactionRequestDTO.getReactionType());
        postReactionRepository.save(postReaction);

        return postReactionMapper.toDTO(postReaction);
    }

    @Override
    @Transactional
    public void deletePostReaction(Long postId, Long reactionId) {
        postService.getEntity(postId);
        postReactionRepository.findById(reactionId).orElseThrow(()->new CustomNotFoundException("post reaction",reactionId));
        postReactionRepository.deleteById(reactionId);
    }


}
