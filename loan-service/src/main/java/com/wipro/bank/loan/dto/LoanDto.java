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

	@NotBlank(message = "Loan type is required")
	private String loanType;
	
	@Positive(message = "Loan amount must be greater than 0")
	private double loanAmount;
	
	@Positive(message = "Interest rate must be greater than 0")
	private double interestRate;
	

	private String loanStatus;
	
	
	//to link loan with customer
	private int customerId;
	 



}
