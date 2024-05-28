package com.paul.api.controllers;

import com.paul.api.models.Account;
import com.paul.api.models.Client;
import com.paul.api.models.Person;
import com.paul.api.repository.AccountRepository;
import com.paul.api.services.AccountService;
import com.paul.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/getAll")
    public List<Account> getAll(){

        return accountRepository.findAll();
    }

    @PostMapping("/createAccount/{id}")
    public Account create(@RequestBody Account account, @PathVariable Long id) {
        Client client = clientService.getClientById(id);
        account.setClient(client);

        return accountService.createAccount(account);
    }
}
