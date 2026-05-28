package com.wipro.bank.account.mapper;

import com.wipro.bank.account.dto.TransactionDto;
import com.wipro.bank.account.entity.Transaction;

public class TransactionMapper {

	public static TransactionDto toDto(Transaction txn) {

		TransactionDto dto = new TransactionDto();

		dto.setTransactionId(txn.getTransactionId());
		dto.setTransactionType(txn.getTransactionType());
		dto.setAmount(txn.getAmount());
		dto.setTransactionDate(txn.getTransactionDate());
		dto.setAccountNumber(txn.getAccountNumber()); 

		return dto;
	}
}