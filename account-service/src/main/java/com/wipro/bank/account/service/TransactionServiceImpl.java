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

	//Deposit money into account
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
		txn.setStatus("SUCCESS");

		// Link transaction with existing account
		txn.setAccountNumber(accountNumber);

		txnRepo.save(txn);


		return "Amount deposited successfully. Updated balance is: " + acc.getBalance();
	}



	//WITHDRAW MONEY FROM ACCOUNT
	@Override
	public String withdraw(String accountNumber, double amount) {

		Account acc = accountRepo.findByAccountNumber(accountNumber);
			
		if (acc == null) return "Account not found";

		/**
		 * Business rule: cannot withdraw more than available balance
		 */
		if (acc.getBalance() < amount) {
			
			Transaction txn = new Transaction();
			
			txn.setTransactionType("WITHDRAW");
			txn.setAmount(amount);
			
			txn.setTransactionDate(LocalDate.now());
			txn.setStatus("FAILED");
			txn.setAccountNumber(accountNumber);
			
			txnRepo.save(txn);
			
			return "Insufficient balance";
		}

		acc.setBalance(acc.getBalance() - amount);
		accountRepo.save(acc);


		Transaction txn = new Transaction();
		txn.setTransactionType("WITHDRAW");
		txn.setAmount(amount);

		txn.setTransactionDate(LocalDate.now());

		txn.setAccountNumber(accountNumber);
		txn.setStatus("SUCCESS");

		txnRepo.save(txn);

		return "Amount withdrawn successfully. Updated balance is: " + acc.getBalance();
	}

	//GET TRANSACTION BY ACCOUNT NUMBER
	@Override
	public List<TransactionDto> getTransactionsHistory(String accountNumber) {

		List<Transaction> list = txnRepo.findByAccountNumber(accountNumber);

		List<TransactionDto> dtoList = new ArrayList<>();

		for (Transaction t : list) {
			dtoList.add(TransactionMapper.toDto(t));
		}

		return dtoList;
	}
}