package com.wipro.bank.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class AccountDto {

	private int accountId;

	@NotBlank(message = "Account number is required")
	private String accountNumber;

	@NotBlank(message = "Account type is required")
	private String accountType;

	@Positive(message = "Balance must be positive")
	private double balance;
	
	@NotBlank(message = "Branch name is required")
	private String branchName;

	
	private int customerId;

	private String status;
	
	//dto.setCustomerId(acc.getCustomerId()); 




}
