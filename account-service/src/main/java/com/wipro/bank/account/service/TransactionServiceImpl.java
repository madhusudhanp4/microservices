package com.wipro.bank.account.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.account.dto.TransactionDto;
import com.wipro.bank.account.entity.Account;
import com.wipro.bank.account.entity.Transaction;
import com.wipro.bank.account.mapper.TransactionMapper;
import com.wipro.bank.account.repository.AccountRepository;
import com.wipro.bank.account.repository.TransactionRepository;

/**
 * Service implementation for handling transaction operations
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository txnRepo;

    @Autowired
    private AccountRepository accountRepo;

    /**
     * Deposit money into account
     */
    @Override
    public String deposit(String accountNumber, double amount) {

        // Fetch account
        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        // Update balance
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        // Create transaction record
        Transaction txn = new Transaction();
        txn.setTransactionType("DEPOSIT");
        txn.setAmount(amount);
        txn.setTransactionDate(LocalDate.now());

        // Link with account
        txn.setAccount(account);

        txnRepo.save(txn);

        return "Amount deposited successfully. Updated balance is: " 
                + account.getBalance();
    }

    /**
     * Withdraw money from account
     */
    @Override
    public String withdraw(String accountNumber, double amount) {

        // Fetch account
        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        // Business rule: insufficient balance
        if (account.getBalance() < amount) {
            return "Insufficient balance";
        }

        // Update balance
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        // Create transaction record
        Transaction txn = new Transaction();
        txn.setTransactionType("WITHDRAW");
        txn.setAmount(amount);
        txn.setTransactionDate(LocalDate.now());

        txn.setAccount(account);

        txnRepo.save(txn);

        return "Amount withdrawn successfully. Updated balance is: " 
                + account.getBalance();
    }

    /**
     * Get all transactions for a given account
     */
    @Override
    public List<TransactionDto> getTransactionsByAccount(String accountNumber) {

        List<Transaction> transactions = 
                txnRepo.findByAccountAccountNumber(accountNumber);

        List<TransactionDto> result = new ArrayList<>();

        for (Transaction txn : transactions) {
            result.add(TransactionMapper.toDto(txn));
        }

        return result;
    }
}
