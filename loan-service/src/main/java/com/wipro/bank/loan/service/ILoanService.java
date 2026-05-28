package com.wipro.bank.loan.service;

import java.util.List;

import com.wipro.bank.loan.dto.LoanDto;

public interface ILoanService {

    //  Bank processes loan request (approve/reject)
    String processLoan(LoanDto dto);

    //  Bank views all loans of a customer (history)
    List<LoanDto> getCustomerLoans(int customerId);

    //  Bank checks customer's total active loan amount
    double getTotalActiveLoanAmount(int customerId);

    //  Bank marks loan as CLOSED (when customer repays)
    String closeLoan(int loanId);

    //  Bank views only ACTIVE loans of a customer
    List<LoanDto> getActiveLoans(int customerId);
}
