package com.wipro.bank.loan.service;

import java.util.List;

import com.wipro.bank.loan.dto.LoanDto;

public interface ILoanService {

    // Process and approve/reject loan request based on eligibility rules
    String applyLoan(LoanDto dto);

    // Get complete loan history of a customer
    List<LoanDto> getLoanHistory(int customerId);

    // Get total outstanding loan amount for a customer
    double getTotalOutstandingLoan(int customerId);

    // Close loan only when all dues are cleared
    String closeLoan(int loanId);

    // Get currently active (ongoing) loans of a customer
    List<LoanDto> getActiveLoans(int customerId);
}