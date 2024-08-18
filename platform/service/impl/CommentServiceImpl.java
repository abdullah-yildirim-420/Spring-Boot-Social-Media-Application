package com.example.temel.service.impl;

import com.example.temel.dto.CommentRequestDTO;
import com.example.temel.dto.CommentResponseDTO;
import com.example.temel.entity.Comment;
import com.example.temel.entity.Person;
import com.example.temel.entity.Post;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.mapper.CommentMapper;
import com.example.temel.messages.ErrorMessages;
import com.example.temel.repository.CommentRepository;
import com.example.temel.service.CommentService;
import com.example.temel.service.PersonService;
import com.example.temel.service.PostService;
import com.example.temel.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final PersonService personService;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService, PersonService personService, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.personService = personService;
        this.commentMapper = commentMapper;
    }


    @Override
    @Transactional
    public CommentResponseDTO create(Long postId, CommentRequestDTO commentRequestDTO){
        Post post = postService.getEntity(postId);
        Person person = personService.getEntityByUsername(commentRequestDTO.getUsername());
        ValidationUtil.validateMatch(person.getPassword(), commentRequestDTO.getPassword(), ErrorMessages.INVALID_PASSWORD);
        Comment comment = commentMapper.toEntity(commentRequestDTO.getCommentContent(),post,person);

        commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public CommentResponseDTO get(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CustomNotFoundException("comment",commentId));
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDTO update(Long commentId, CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CustomNotFoundException("comment",commentId));
        Person person = comment.getPerson();
        ValidationUtil.validateMatch(person.getUsername(),commentRequestDTO.getUsername(),ErrorMessages.INVALID_USERNAME);
        ValidationUtil.validateMatch(person.getPassword(), commentRequestDTO.getPassword(),ErrorMessages.INVALID_PASSWORD);

        comment.setCommentContent(commentRequestDTO.getCommentContent());
        commentRepository.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public Comment getEntity(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(()->new CustomNotFoundException("comment",commentId));
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(()->new CustomNotFoundException("comment",commentId));
        commentRepository.deleteById(commentId);
    }



}
