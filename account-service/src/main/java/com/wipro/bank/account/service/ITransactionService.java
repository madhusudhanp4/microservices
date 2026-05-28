package com.wipro.bank.account.service;

import java.util.List;

import com.wipro.bank.account.dto.TransactionDto;

public interface ITransactionService {


	String deposit(String accountNumber, double amount);

	String withdraw(String accountNumber, double amount);

	List<TransactionDto> getTransactionsByAccount(String accountNumber);


}
