package com.example.temel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequestDTO {


    @NotBlank(message = "Comment content cannot be blank")
    @Size(min = 2, max = 500, message = "Comment content must be between 2 and 500 characters long")
    private String commentContent;

    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Username can only contain letters, numbers, dots, hyphens, and underscores")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters long")
    private final String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3, max = 25, message = "Password must be between 3 and 25 characters long")
    private final String password;


}
