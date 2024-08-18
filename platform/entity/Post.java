package com.example.temel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post extends BaseEntity {

    @Column(nullable = false)
    @NotBlank(message = "Post title cannot be blank")
    @Size(min = 2, max = 100, message = "Post title must be between 2 and 100 characters long")
    private String postTitle;

    @Column(nullable = false)
    @NotBlank(message = "Post content cannot be blank")
    @Size(min = 2, max = 1000, message = "Post content must be between 2 and 1000 characters long")
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Post_Person"))
    private Person person;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostReaction> postReactions = new ArrayList<>();

    public Post() {

    }

    public Post(String postTitle, String postContent, Person person) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.person = person;
    }

}
