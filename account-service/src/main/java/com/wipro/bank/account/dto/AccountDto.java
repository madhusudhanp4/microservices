package com.wipro.bank.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class AccountDto {

	private int accountId;

	private String accountNumber;

	@NotBlank(message = "Account type is required")
	@Pattern(regexp = "^(SAVINGS|CURRENT|LOAN)$", message = "Account type must be SAVINGS, CURRENT or LOAN")
	private String accountType;


	@PositiveOrZero(message = "Balance cannot be negative")
	private double balance;


	@NotBlank(message = "Branch name is required")
	private String branchName;

	// to link account with customer
	private int customerId;

	private String status;




}
