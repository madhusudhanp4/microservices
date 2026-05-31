package com.wipro.bank.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.bank.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	/*
	 * Fetch loans of a specific customer instead of getting all records 
	 * Helps improve performance and apply customer-based logic easily
	 */

	List<Loan> findByCustomerId(int customerId);

	List<Loan> findByCustomerIdAndLoanStatus(int customerId, String loanStatus);
}
