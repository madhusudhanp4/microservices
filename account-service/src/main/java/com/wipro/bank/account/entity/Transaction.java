package com.wipro.bank.account.entity;

import java.time.LocalDate;

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
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;

	private String transactionType;
	private double amount;
	private LocalDate transactionDate;
	

	private String accountNumber;
	
	private String status;

	/*
	 * //Many transactions belong to one account
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "account_id") private Account account;
	 */


}
