package com.wipro.bank.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.account.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {	

	
	/*
	 * Used to get all transactions of a specific account 
	 * Helps show transaction history and track account activity
	 */
	List<Transaction> findByAccountNumber(String accountNumber);
}
