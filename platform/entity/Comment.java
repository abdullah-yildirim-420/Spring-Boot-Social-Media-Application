package com.example.temel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Comment extends BaseEntity {

    @Column(nullable = false)
    @NotBlank(message = "Comment content cannot be blank")
    @Size(min = 2, max = 500, message = "Comment content must be between 2 and 500 characters long")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Comment_Person"))
    private Person person;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Comment_Post"))
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentReaction> commentReactions = new ArrayList<>();

    public Comment(){

    }

    public Comment(String content, Post post, Person person) {
        this.commentContent = content;
        this.post = post;
        this.person = person;
    }

}
