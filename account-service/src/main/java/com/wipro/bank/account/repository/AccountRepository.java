package com.wipro.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	//JPA methods work only on primary key
	
	/*
	 * We use this method to find account by accountNumber 
	 * because user works with accountNumber, not accountId
	 */
	Account findByAccountNumber(String accountNumber);
}
