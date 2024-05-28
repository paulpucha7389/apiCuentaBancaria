package com.paul.api.controllers;

import com.paul.api.models.Person;
import com.paul.api.repository.PersonRepository;
import com.paul.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/getAll")
    public List<Person> getAll(Person person){
        return personRepository.findAll();
    }

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person){
        return personService.create(person);
    }
}
