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

    //  Process loan (approve/reject)
    @PostMapping("/process")
    public String process(@Valid @RequestBody LoanDto dto) {
        return service.processLoan(dto);
    }

    //  Get loan history
    @GetMapping("/customer/{customerId}")
    public List<LoanDto> getLoans(@PathVariable int customerId) {
        return service.getCustomerLoans(customerId);
    }

    //  Get active loans
    @GetMapping("/active/{customerId}")
    public List<LoanDto> getActive(@PathVariable int customerId) {
        return service.getActiveLoans(customerId);
    }

    //  Get total loan amount
    @GetMapping("/total/{customerId}")
    public double getTotal(@PathVariable int customerId) {
        return service.getTotalActiveLoanAmount(customerId);
    }

    //  Close loan
    @PutMapping("/close/{loanId}")
    public String close(@PathVariable int loanId) {
        return service.closeLoan(loanId);
    }
}