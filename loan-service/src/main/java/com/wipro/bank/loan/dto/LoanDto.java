package com.wipro.bank.loan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class LoanDto {

	private int loanId;

	@NotBlank(message = "Loan type is required")
	private String loanType;
	
	@Positive(message = "Loan amount must be positive")
	private double loanAmount;
	
	@Positive(message = "Interest rate must be positive")
	private double interestRate;
	
	
	private int customerId;
	
	private String loanStatus; 



}
