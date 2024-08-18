package com.example.temel.service.impl;

import com.example.temel.dto.*;
import com.example.temel.entity.Person;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.mapper.PersonMapper;
import com.example.temel.messages.ErrorMessages;
import com.example.temel.repository.PersonRepository;
import com.example.temel.service.PersonService;
import com.example.temel.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    @Transactional
    public PersonResponseDTO create(PersonRequestDTO personRequestDTO) {
        Person person = personMapper.toEntity(personRequestDTO);
        personRepository.save(person);
        return personMapper.toDTO(person);
    }

    @Override
    public PersonResponseDTO get(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()->new CustomNotFoundException("person",id));
        return personMapper.toDTO(person);
    }

    @Override
    public List<PersonListResponseDTO> getAll() {
        List<Person> persons = personRepository.findAll();
        if(persons.isEmpty()){
            throw new CustomNotFoundException(ErrorMessages.NO_PERSONS_FOUND);
        }
        return persons.stream()
                .map(personMapper::toListDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personRepository.findById(id).orElseThrow(()->new CustomNotFoundException("person",id));
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PersonResponseDTO updateUsername(Long id, UpdateUsernameRequestDTO updateUsernameRequestDTO) {
        Person person = personRepository.findById(id).orElseThrow(()->new CustomNotFoundException("person",id));

        ValidationUtil.validateMatch(person.getUsername(), updateUsernameRequestDTO.getCurrentUsername(), ErrorMessages.INVALID_USERNAME);
        ValidationUtil.validateMatch(person.getPassword(),updateUsernameRequestDTO.getCurrentPassword(),ErrorMessages.INVALID_PASSWORD);
        ValidationUtil.validateNotSame(person.getUsername(),updateUsernameRequestDTO.getNewUsername(),ErrorMessages.SAME_USERNAME);

        person.setUsername(updateUsernameRequestDTO.getNewUsername());

        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    @Transactional
    public PersonResponseDTO updateEmail(Long id, UpdateEmailRequestDTO updateEmailRequestDTO) {
        Person person = personRepository.findById(id).orElseThrow(()->new CustomNotFoundException("person",id));

        ValidationUtil.validateMatch(person.getEmail(), updateEmailRequestDTO.getCurrentEmail(),ErrorMessages.INVALID_EMAIL);
        ValidationUtil.validateMatch(person.getPassword(), updateEmailRequestDTO.getCurrentPassword(),ErrorMessages.INVALID_PASSWORD);
        ValidationUtil.validateNotSame(person.getEmail(), updateEmailRequestDTO.getNewEmail(),ErrorMessages.SAME_EMAIL);
        person.setEmail(updateEmailRequestDTO.getNewEmail());

        return personMapper.toDTO(personRepository.save(person));
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDTO updatePasswordRequestDTO) {
        Person person = personRepository.findById(id).orElseThrow(()->new CustomNotFoundException("person",id));

        ValidationUtil.validateMatch(person.getUsername(), updatePasswordRequestDTO.getCurrentUsername(),ErrorMessages.INVALID_USERNAME);
        ValidationUtil.validateMatch(person.getPassword(), updatePasswordRequestDTO.getCurrentPassword(),ErrorMessages.INVALID_PASSWORD);
        ValidationUtil.validateNotSame(person.getPassword(),updatePasswordRequestDTO.getNewPassword(),ErrorMessages.SAME_PASSWORD);
        person.setPassword(updatePasswordRequestDTO.getNewPassword());

        personRepository.save(person);
    }

    @Override
    public Person getEntity(Long id){
        return personRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("person",id));
    }

    @Override
    public Person getEntityByUsername(String username){
        return personRepository.findByUsername(username)
                .orElseThrow(()->new CustomNotFoundException("person",username));
    }




}
