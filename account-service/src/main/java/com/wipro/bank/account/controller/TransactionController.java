package com.wipro.bank.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bank.account.dto.TransactionDto;
import com.wipro.bank.account.service.ITransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService service;

    //  Deposit
    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.deposit(accountNumber, amount);
    }

    //  Withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return service.withdraw(accountNumber, amount);
    }

    //  Get transaction history
    @GetMapping("/{accountNumber}")
    public List<TransactionDto> getTransactions(@PathVariable String accountNumber) {
        return service.getTransactionsHistory(accountNumber);
    }
}