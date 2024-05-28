package com.paul.api.controllers;


import com.paul.api.models.Client;
import com.paul.api.models.Person;
import com.paul.api.repository.ClientRepository;
import com.paul.api.services.ClientService;
import com.paul.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private PersonService personService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/getAll")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @PostMapping("/createClient/{id}")
    public Client create(@RequestBody Client client, @PathVariable Long id) {
        Person person = personService.getPersonById(id);
        client.setPerson(person);

        return clientService.createClient(client);
    }
}
