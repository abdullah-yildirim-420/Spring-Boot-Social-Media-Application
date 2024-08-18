package com.example.temel.entity;

import com.example.temel.enums.ReactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class PostReaction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Reaction type cannot be null")
    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PostReaction_Person"))
    private Person person;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PostReaction_Post"))
    private Post post;

    public PostReaction() {

    }

    public PostReaction(ReactionType reactionType, Person person, Post post) {
        this.reactionType = reactionType;
        this.person = person;
        this.post = post;
    }

}
