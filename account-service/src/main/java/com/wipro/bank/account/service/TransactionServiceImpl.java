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

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository txnRepo;
    
    //Repo to interact with account
    @Autowired
    private AccountRepository accountRepo;

    //Deposit money to account
    @Override
    public String deposit(String accountNumber, double amount) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null) return "Account not found";

        acc.setBalance(acc.getBalance() + amount);
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setTransactionType("DEPOSIT");
        txn.setAmount(amount);
        txn.setTransactionDate(LocalDate.now());
        txn.setAccountNumber(accountNumber);


        txnRepo.save(txn);

        return "Amount deposited";
    }

    //WITHDRAW MONEY FROM ACCOUNT
    @Override
    public String withdraw(String accountNumber, double amount) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null) return "Account not found";

        if (acc.getBalance() < amount) {
            return "Insufficient balance";
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setTransactionType("WITHDRAW");
        txn.setAmount(amount);
        txn.setTransactionDate(LocalDate.now());
        txn.setAccountNumber(accountNumber);
        

        txnRepo.save(txn);

        return "Amount withdrawn";
    }

    //GET TRANSACTION BY ACCOUNT NUMBER
    @Override
    public List<TransactionDto> getTransactionsByAccount(String accountNumber) {

    	List<Transaction> list = txnRepo.findAll();
    	List<TransactionDto> result = new ArrayList<>();

    	for (Transaction t : list) {

    	    if (t.getAccountNumber().equals(accountNumber)) {

    	        result.add(TransactionMapper.toDto(t));
    	    }
    	}

        return result;
    }
}