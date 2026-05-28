package com.wipro.bank.account.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class TransactionDto {

	private int transactionId;

	@NotBlank(message = "Transaction type required")
	private String transactionType;
	
	@Positive(message = "Amount must be positive")
	private double amount;
	
	
	private LocalDate transactionDate;
	
	private String accountNumber;



}
