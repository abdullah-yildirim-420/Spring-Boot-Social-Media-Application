package com.example.temel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUsernameRequestDTO {

    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "New username can only contain letters, numbers, dots, hyphens, and underscores")
    @NotBlank(message = "New username cannot be blank")
    @Size(min = 3, max = 25, message = "New username must be between 3 and 25 characters long")
    private final String newUsername;

    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Current username can only contain letters, numbers, dots, hyphens, and underscores")
    @NotBlank(message = "Current username cannot be blank")
    @Size(min = 3, max = 25, message = "Current username must be between 3 and 25 characters long")
    private final String currentUsername;

    @NotBlank(message = "Current password cannot be blank")
    @Size(min = 3, max = 25, message = "Current password must be between 3 and 25 characters long")
    private final String currentPassword;


}
