package com.example.temel.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PersonListResponseDTO {

    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public PersonListResponseDTO(String firstName, String lastName, LocalDate dateOfBirth, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
