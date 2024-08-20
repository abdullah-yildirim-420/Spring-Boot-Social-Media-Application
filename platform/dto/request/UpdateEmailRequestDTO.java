package com.example.temel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateEmailRequestDTO {

    @Email(message = "New email should be valid")
    @NotBlank(message = "New email cannot be blank")
    @Size(min = 5, max = 50, message = "New email must be between 5 and 50 characters long")
    private final String newEmail;

    @Email(message = "Current email should be valid")
    @NotBlank(message = "Current email cannot be blank")
    @Size(min = 5, max = 50, message = "Current email must be between 5 and 50 characters long")
    private final String currentEmail;

    @NotBlank(message = "Current password cannot be blank")
    @Size(min = 3, max = 25, message = "Current password must be between 3 and 25 characters long")
    private final String currentPassword;

}
