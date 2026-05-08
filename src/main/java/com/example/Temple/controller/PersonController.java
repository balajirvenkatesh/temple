package com.example.Temple.controller;

import com.example.Temple.model.Person;
import com.example.Temple.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson(){
        List<Person> personList = personService.getAllPerson();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<?> getPerson(@RequestParam Long id){
        Optional<Person> optionalPerson = personService.getPerson(id);
        if(optionalPerson.isPresent()){
            return new ResponseEntity<>(optionalPerson.get(), HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("given Id "+ id+ " not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestParam Long id, @RequestBody Person person){
        try{
            Person updatedPerson = personService.updatePerson(id, person);
            return new ResponseEntity<>(updatedPerson, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePerson(@RequestParam Long id){
        try{
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
