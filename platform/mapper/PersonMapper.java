package com.example.temel.mapper;

import com.example.temel.dto.PersonListResponseDTO;
import com.example.temel.dto.PersonRequestDTO;
import com.example.temel.dto.PersonResponseDTO;
import com.example.temel.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequestDTO personRequestDTO){
        return new Person(
                personRequestDTO.getFirstName(),
                personRequestDTO.getLastName(),
                personRequestDTO.getDateOfBirth(),
                personRequestDTO.getUsername(),
                personRequestDTO.getPassword(),
                personRequestDTO.getEmail(),
                personRequestDTO.getPhone()
        );
    }

    public PersonResponseDTO toDTO(Person person){
        return new PersonResponseDTO(
                person.getFirstName(),
                person.getLastName(),
                person.getDateOfBirth(),
                person.getUsername(),
                person.getEmail(),
                person.getPhone(),
                person.getCreatedAt(),
                person.getUpdatedAt()
        );
    }

    public PersonListResponseDTO toListDTO(Person person){
        return new PersonListResponseDTO(
                person.getFirstName(),
                person.getLastName(),
                person.getDateOfBirth(),
                person.getUsername(),
                person.getCreatedAt(),
                person.getUpdatedAt()
        );

    }


}
