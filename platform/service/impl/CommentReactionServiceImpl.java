package com.example.temel.service.impl;

import com.example.temel.dto.ReactionRequestDTO;
import com.example.temel.dto.ReactionResponseDTO;
import com.example.temel.entity.Comment;
import com.example.temel.entity.CommentReaction;
import com.example.temel.entity.Person;
import com.example.temel.exception.CustomAlreadyExistException;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.mapper.CommentReactionMapper;
import com.example.temel.messages.ErrorMessages;
import com.example.temel.repository.CommentReactionRepository;
import com.example.temel.service.CommentReactionService;
import com.example.temel.service.CommentService;
import com.example.temel.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentReactionServiceImpl implements CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final CommentService commentService;
    private final PersonServiceImpl personService;
    private final CommentReactionMapper commentReactionMapper;

    public CommentReactionServiceImpl(CommentReactionRepository commentReactionRepository, CommentService commentService, PersonServiceImpl personService, CommentReactionMapper commentReactionMapper) {
        this.commentReactionRepository = commentReactionRepository;
        this.commentService = commentService;
        this.personService = personService;
        this.commentReactionMapper = commentReactionMapper;
    }


    @Override
    @Transactional
    public ReactionResponseDTO create(Long commentId, ReactionRequestDTO reactionRequestDTO) {
        Comment comment = commentService.getEntity(commentId);
        Person person = personService.getEntityByUsername(reactionRequestDTO.getUsername());

        ValidationUtil.validateMatch(person.getPassword(), reactionRequestDTO.getPassword(), ErrorMessages.INVALID_PASSWORD);

        commentReactionRepository.findByPersonIdAndCommentId(person.getId(),commentId).ifPresent(reaction->{
            throw new CustomAlreadyExistException(ErrorMessages.REACTION_EXIST);
        });
        CommentReaction commentReaction = commentReactionMapper.toEntity(reactionRequestDTO,person,comment);
        commentReactionRepository.save(commentReaction);
        return commentReactionMapper.toDTO(commentReaction);
    }

    @Override
    @Transactional
    public ReactionResponseDTO update(Long commentId, ReactionRequestDTO reactionRequestDTO) {
        commentService.getEntity(commentId);
        Person person = personService.getEntityByUsername(reactionRequestDTO.getUsername());

        ValidationUtil.validateMatch(person.getPassword(), reactionRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        CommentReaction commentReaction = commentReactionRepository.findByPersonIdAndCommentId(person.getId(),commentId).orElseThrow(()->new CustomNotFoundException(ErrorMessages.REACTION_NOT_FOUND));
        commentReaction.setReactionType(reactionRequestDTO.getReactionType());
        commentReactionRepository.save(commentReaction);

        return commentReactionMapper.toDTO(commentReaction);
    }

    @Override
    @Transactional
    public void deleteCommentReaction(Long commentId, Long reactionId) {
        commentService.getEntity(commentId);
        commentReactionRepository.findById(reactionId).orElseThrow(()->new CustomNotFoundException("comment reaction",reactionId));
        commentReactionRepository.deleteById(commentId);
    }




}
