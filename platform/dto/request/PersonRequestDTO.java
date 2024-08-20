package com.example.temel.dto;

import com.example.temel.validator.Adult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonRequestDTO {

    @Pattern(regexp = "^[A-Za-z]+$", message = "First name can only contain letters")
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters long")
    private final String firstName;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name can only contain letters")
    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters long")
    private final String lastName;

    @Adult
    private final LocalDate dateOfBirth;

    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Username can only contain letters, numbers, dots, hyphens, and underscores")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters long")
    private final String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3, max = 25, message = "Password must be between 3 and 25 characters long")
    private final String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters long")
    private final String email;

    @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone number can only contain numbers and optional leading +")
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters long")
    private final String phone;


}
