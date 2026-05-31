package com.wipro.bank.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bank.loan.dto.LoanDto;
import com.wipro.bank.loan.service.ILoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private ILoanService service;

    // Apply for loan (approve / reject based on rules)
    @PostMapping("/apply")
    public String applyLoan(@Valid @RequestBody LoanDto dto) {

        return service.applyLoan(dto);
    }

    // Get complete loan history of a customer
    @GetMapping("/history/{customerId}")
    public List<LoanDto> getHistory(@PathVariable int customerId) {

        return service.getLoanHistory(customerId);
    }

    // Get only active loans of a customer
    @GetMapping("/active/{customerId}")
    public List<LoanDto> getActive(@PathVariable int customerId) {

        return service.getActiveLoans(customerId);
    }

    // Get total outstanding loan amount
    @GetMapping("/total/{customerId}")
    public double getTotalLoan(@PathVariable int customerId) {

        return service.getTotalOutstandingLoan(customerId);
    }

    // Close loan (after repayments)
    @PutMapping("/close/{loanId}")
    public String closeLoan(@PathVariable int loanId) {

        return service.closeLoan(loanId);
    }
}
