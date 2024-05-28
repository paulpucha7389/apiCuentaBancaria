package com.paul.api.controllers;

import com.paul.api.models.Account;
import com.paul.api.models.Client;
import com.paul.api.models.Transaction;
import com.paul.api.services.AccountService;
import com.paul.api.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAll")
    public List<Transaction> getAll(){

        return transactionService.getAll();
    }

    @PostMapping("/createTransaction")
    public Transaction create(@RequestBody Transaction transaction) {
        //Account account = accountService.getAccountById(transaction.getAccount().getId());
        //transaction.setAccount(account);
        return transactionService.createTransaction(transaction);
    }

    @PostMapping("/deposit")
    public Transaction deposit(@RequestBody Transaction transaction) {
            //transactionService.createTransaction(transaction);
            return transactionService.deposit(transaction);
            //transactionService.withdrawal();
    }

    @PostMapping("/withdrawal")
    public Transaction withdrawal(@RequestBody Transaction transaction) {

        //transactionService.createTransaction(transaction);
        return transactionService.withdrawal(transaction);
        //transactionService.withdrawal();
    }
}
