package com.example.temel.repository;

import com.example.temel.entity.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction,Long> {


    Optional<PostReaction> findByPersonIdAndPostId(Long id, Long postId);
}
