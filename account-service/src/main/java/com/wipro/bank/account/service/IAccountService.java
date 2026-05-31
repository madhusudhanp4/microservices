package com.wipro.bank.account.service;

import java.util.List;

import com.wipro.bank.account.dto.AccountDto;

public interface IAccountService {

	// Open account
	String createAccount(AccountDto dto);

	// Get account details (by account number)
	AccountDto getAccount(String accountNumber);
	

	List<AccountDto> getAllAccounts();
	
	double getBalance(String accountNumber);
}

