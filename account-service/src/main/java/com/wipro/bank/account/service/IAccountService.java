package com.wipro.bank.account.service;

import java.util.List;

import com.wipro.bank.account.dto.AccountDto;

public interface IAccountService {

	// Open account
	AccountDto createAccount(AccountDto dto);

	// Get account details (by account number - safer)
	AccountDto getAccountByNumber(String accountNumber);
	

	List<AccountDto> getAllAccounts();

	// Check balance
	double getBalancebyNumber(String accountNumber);

	// Update limited details (optional)
	AccountDto updateAccountDetails(String accountNumber, AccountDto dto);

	String closeAccount(String accountNumber);


}

