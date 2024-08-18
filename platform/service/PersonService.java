package com.example.temel.service;

import com.example.temel.dto.*;
import com.example.temel.entity.Person;

import java.util.List;

public interface PersonService {
    PersonResponseDTO create(PersonRequestDTO personRequestDTO);


    PersonResponseDTO get(Long id);

    void delete(Long id);

    PersonResponseDTO updateUsername(Long id, UpdateUsernameRequestDTO updateUsernameRequestDTO);

    PersonResponseDTO updateEmail(Long id, UpdateEmailRequestDTO updateEmailRequestDTO);

    void updatePassword(Long id, UpdatePasswordRequestDTO updatePasswordRequestDTO);

    List<PersonListResponseDTO> getAll();

    Person getEntity(Long id);

    Person getEntityByUsername(String username);
}
