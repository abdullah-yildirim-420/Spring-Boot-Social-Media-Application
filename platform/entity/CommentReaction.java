package com.example.temel.entity;

import com.example.temel.enums.ReactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class CommentReaction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Reaction type cannot be null")
    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CommentReaction_Person"))
    private Person person;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CommentReaction_Comment"))
    private Comment comment;

    public CommentReaction(ReactionType reactionType, Person person, Comment comment) {
        this.reactionType = reactionType;
        this.person = person;
        this.comment = comment;
    }

    public CommentReaction(){

    }

}
