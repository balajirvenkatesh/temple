package com.example.Temple.service;

import com.example.Temple.model.Person;
import com.example.Temple.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(Long id){
        return personRepository.findById(id);
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }
    public Person updatePerson(Long id, Person updatePersonDetails) throws Exception {
        Optional<Person> existingPerson = getPerson(id);
        if(existingPerson.isPresent()){
            Person existingPersonInDb = existingPerson.get();
            existingPersonInDb.setUpdatedAt(LocalDateTime.now());
            existingPersonInDb.setEmail(updatePersonDetails.getEmail());
            existingPersonInDb.setGender(updatePersonDetails.getGender());
            existingPersonInDb.setFirstName(updatePersonDetails.getFirstName());
            existingPersonInDb.setLastName(updatePersonDetails.getLastName());
            existingPersonInDb.setDateOfBirth(updatePersonDetails.getDateOfBirth());
            existingPersonInDb.setPhoneNumber(updatePersonDetails.getPhoneNumber());
            return personRepository.save(existingPersonInDb);
        }
        else {
            throw new EntityNotFoundException("Person id "+id+" is no present");
        }
    }

    public void deletePerson(Long id){
        Optional<Person> optionalPerson =  getPerson(id);
        if(optionalPerson.isPresent()){
            Person existingPerson = optionalPerson.get();
            existingPerson.setDeletedAt(LocalDateTime.now());
            personRepository.save(existingPerson);
        }
        else {
            throw new EntityNotFoundException("Person id "+id+" is not presente");
        }
    }
}
