package com.wipro.bank.account.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {

	@NotBlank(message = "Transaction type is required")
	@Pattern(regexp = "^(DEPOSIT|WITHDRAW)$",
	message = "Transaction type must be DEPOSIT or WITHDRAW")
	private String transactionType;
	

	@Positive(message = "Amount must be greater than 0")
	private double amount;


	@NotBlank(message = "Account number is required")
	private String accountNumber;


	private String status;

}
