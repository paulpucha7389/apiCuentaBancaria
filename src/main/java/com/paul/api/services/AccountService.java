package com.paul.api.services;

import com.paul.api.models.Account;
import com.paul.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account)
    {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
