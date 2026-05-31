package com.wipro.bank.account.mapper;

import com.wipro.bank.account.dto.TransactionDto;
import com.wipro.bank.account.entity.Transaction;

public class TransactionMapper {

	//Entity -> DTO (retrieval)
    public static TransactionDto toDto(Transaction txn) {

        TransactionDto dto = new TransactionDto();
        dto.setTransactionType(txn.getTransactionType());
        dto.setAmount(txn.getAmount());
        dto.setAccountNumber(txn.getAccountNumber());
        dto.setStatus(txn.getStatus());

        return dto;
    }
    
    //DTO -> Entity
    public static Transaction toEntity(TransactionDto dto) {
    	
    	Transaction ts = new Transaction();
    	ts.setTransactionType(dto.getTransactionType());
    	ts.setAmount(dto.getAmount());
    	ts.setAccountNumber(dto.getAccountNumber());
    	ts.setStatus(dto.getStatus());
    	
		return ts;
    }
}