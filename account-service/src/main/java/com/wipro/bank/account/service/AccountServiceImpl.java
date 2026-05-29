package com.wipro.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.account.dto.AccountDto;
import com.wipro.bank.account.entity.Account;
import com.wipro.bank.account.mapper.AccountMapper;
import com.wipro.bank.account.repository.AccountRepository;

/*
 * Service implementation for Account operations.
 * Handles business logic related to accounts.
 */

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepo;

    
    /*
     * Create a new account for an existing customer.
     * (Here we assume customerId is valid — no external call yet)
     */
    
    
    @Override
    public AccountDto createAccount(AccountDto dto) {

        // Convert DTO to Entity
        Account account = AccountMapper.toEntity(dto);

        // Generate account number automatically
        String accountNumber = "ACC" + System.currentTimeMillis();
        account.setAccountNumber(accountNumber);

        // Set default status
        account.setStatus("ACTIVE");

        // Save to database
        Account savedAccount = accountRepo.save(account);

        // Convert back to DTO and return
        return AccountMapper.toDto(savedAccount);
    }

    /**
     * Fetch account details using account number
     */
    @Override
    public AccountDto getAccountByNumber(String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null || "CLOSED".equals(account.getStatus())) {
            return null;
        }

        return AccountMapper.toDto(account);
    }

    /**
     * Fetch all active accounts
     */
    
    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepo.findAll();
        List<AccountDto> result = new ArrayList<>();

        for (Account account : accounts) {

            if (!"CLOSED".equals(account.getStatus())) {
                result.add(AccountMapper.toDto(account));
            }
        }

        return result;
    }

    /**
     * Get current balance of an account
     */
    @Override
    public double getBalancebyNumber(String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return 0;
        }

        return account.getBalance();
    }

    /**
     * Update account details (only allowed if account is active)
     */
    @Override
    public AccountDto updateAccountDetails(String accountNumber, AccountDto dto) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null || "CLOSED".equals(account.getStatus())) {
            return null;
        }

        // Update only required fields
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setBranchName(dto.getBranchName());

        Account updatedAccount = accountRepo.save(account);

        return AccountMapper.toDto(updatedAccount);
    }
    
    
    

    /**
     * Close an account (soft delete using status)
     */
    @Override
    public String closeAccount(String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        if ("CLOSED".equals(account.getStatus())) {
            return "Account already closed";
        }

        account.setStatus("CLOSED");
        accountRepo.save(account);

        return "Account closed successfully";
    }
}
