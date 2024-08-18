package com.example.temel.repository;

import com.example.temel.entity.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction,Long> {
    Optional <CommentReaction> findByPersonIdAndCommentId(Long id, Long commentId);
}
