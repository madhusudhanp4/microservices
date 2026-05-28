package com.wipro.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.account.dto.AccountDto;
import com.wipro.bank.account.entity.Account;
import com.wipro.bank.account.mapper.AccountMapper;
import com.wipro.bank.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public AccountDto createAccount(AccountDto dto) {

        //  NO customer fetch
        Account acc = AccountMapper.toEntity(dto);

        Account saved = accountRepo.save(acc);

        return AccountMapper.toDto(saved);
    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null || "CLOSED".equals(acc.getStatus()))
            return null;

        return AccountMapper.toDto(acc);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> list = accountRepo.findAll();
        List<AccountDto> result = new ArrayList<>();

        for (Account acc : list) {

            if ("CLOSED".equals(acc.getStatus()))
                continue;

            result.add(AccountMapper.toDto(acc));
        }

        return result;
    }

    @Override
    public double getBalancebyNumber(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null)
            return 0;

        return acc.getBalance();
    }

    @Override
    public AccountDto updateAccountDetails(String accountNumber, AccountDto dto) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null || "CLOSED".equals(acc.getStatus()))
            return null;

        acc.setAccountType(dto.getAccountType());
        acc.setBalance(dto.getBalance());
        acc.setBranchName(dto.getBranchName());

        Account updated = accountRepo.save(acc);

        return AccountMapper.toDto(updated);
    }

    @Override
    public String closeAccount(String accountNumber) {

        Account acc = accountRepo.findByAccountNumber(accountNumber);

        if (acc == null)
            return "Account not found";

        if ("CLOSED".equals(acc.getStatus()))
            return "Already closed";

        acc.setStatus("CLOSED");

        accountRepo.save(acc);

        return "Account closed successfully ";
    }
}
