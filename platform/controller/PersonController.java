package com.example.temel.controller;

import com.example.temel.dto.*;
import com.example.temel.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<PersonResponseDTO> create(@Valid @RequestBody PersonRequestDTO personRequestDTO){
        PersonResponseDTO personResponseDTO = personService.create(personRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> get(@PathVariable Long id){
        PersonResponseDTO personResponseDTO = personService.get(id);
        return ResponseEntity.ok(personResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonListResponseDTO>> getAll(){
        List<PersonListResponseDTO> personList = personService.getAll();
        return ResponseEntity.ok(personList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<String> updateUsername(@PathVariable Long id,@Valid @RequestBody UpdateUsernameRequestDTO updateUsernameRequestDTO){
        PersonResponseDTO updatedPerson = personService.updateUsername(id,updateUsernameRequestDTO);
        String successMessage = "Username updated successfully. New username is: "+updatedPerson.getUsername();
        return ResponseEntity.ok(successMessage);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<String> updateEmail(@PathVariable Long id,@Valid @RequestBody UpdateEmailRequestDTO updateEmailRequestDTO){
        PersonResponseDTO updatedPerson = personService.updateEmail(id,updateEmailRequestDTO);
        String successMessage = "Email updated successfully. New email is: "+updatedPerson.getEmail();
        return ResponseEntity.ok(successMessage);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequestDTO updatePasswordRequestDTO){
        personService.updatePassword(id,updatePasswordRequestDTO);
        String successMessage = "Password updated successfully.";
        return ResponseEntity.ok(successMessage);
    }




}
