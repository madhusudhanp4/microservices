package com.wipro.bank.customer.entity;

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
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	private String customerName;
	private String mobile;
	private String email;
	private String address;

	private String status;


	/*
	 * //One customer can have multiple accounts
	 * 
	 * @OneToMany(mappedBy = "customer") private List<Account> accounts;
	 * 
	 * //One customer can have multiple loans
	 * 
	 * @OneToMany(mappedBy = "customer") private List<Loan> loans;
	 * 
	 */


}
