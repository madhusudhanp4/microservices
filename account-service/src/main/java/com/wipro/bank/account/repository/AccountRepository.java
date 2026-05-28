package com.wipro.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	
	Account findByAccountNumber(String accountNumber);
}
