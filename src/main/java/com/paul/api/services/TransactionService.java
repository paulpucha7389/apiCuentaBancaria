package com.paul.api.services;

import com.paul.api.models.Account;
import com.paul.api.models.Transaction;
import com.paul.api.repository.AccountRepository;
import com.paul.api.repository.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@Transactional
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transaction> getAll() {
        return transactionsRepository.findAll();
    }



    public Transaction getTransactionById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaccion no encontrada"));
    }

    @Transactional
    public Transaction deposit(Transaction transaction) {


            Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
            account.setInitialBalance(account.getInitialBalance() + transaction.getValue().doubleValue());
            accountRepository.save(account);
            transaction.setBalance(account.getInitialBalance());
            return transactionsRepository.save(transaction);
        }

    public Transaction withdrawal(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        double newBalance = account.getInitialBalance() - transaction.getValue().doubleValue();
        if (transaction.getType().equals("retiro")) {

            if (newBalance < 0) {
                throw new RuntimeException("Fondos insuficientes");
            }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);
        transaction.setBalance(account.getInitialBalance());
        return transactionsRepository.save(transaction);
        }else{
            throw new RuntimeException("Tipo transacion no existe");
        }
    }

    public Transaction createTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        if (transaction.getType().equals("deposito")){
            account.setInitialBalance(account.getInitialBalance() + transaction.getValue().doubleValue());
            accountRepository.save(account);
            transaction.setBalance(account.getInitialBalance());
            return transactionsRepository.save(transaction);
        } else if (transaction.getType().equals("retiro")) {
            double newBalance = account.getInitialBalance() - transaction.getValue();
            double valueWithdrawal = transaction.getValue() * -1;
            if (newBalance < 0) {
                throw new RuntimeException("Fondos insuficientes");
            }

            account.setInitialBalance(newBalance);
            accountRepository.save(account);
            transaction.setValue(valueWithdrawal);
            transaction.setBalance(account.getInitialBalance());
            return transactionsRepository.save(transaction);
        }else{
            throw new RuntimeException("Tipo transacion no existe");
        }

    }

}
