package com.wipro.bank.account.mapper;

import com.wipro.bank.account.dto.AccountDto;
import com.wipro.bank.account.entity.Account;

public class AccountMapper {

	public static AccountDto toDto(Account acc) {

	    AccountDto dto = new AccountDto();

	    dto.setAccountType(acc.getAccountType());
	    dto.setBalance(acc.getBalance());
	    dto.setBranchName(acc.getBranchName());
	    
	    dto.setStatus(acc.getStatus());

	    dto.setCustomerId(acc.getCustomerId()); 

	    return dto;
	}

	public static Account toEntity(AccountDto dto) {
	   
		Account acc = new Account();
	    
	    acc.setAccountType(dto.getAccountType());
	    acc.setBalance(dto.getBalance());
	    acc.setBranchName(dto.getBranchName());
	    
	    acc.setStatus(dto.getStatus());
	    
	    acc.setCustomerId(dto.getCustomerId());
	    
	    return acc;
	}
}
