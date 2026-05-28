package com.wipro.bank.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	private String loanType;
	private double loanAmount;
	private double interestRate;
    private String loanStatus;

	
    private int customerId;
    
    
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "customer_id") private Customer customer;
	 */

	
	
}
