package com.wipro.bank.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.loan.dto.LoanDto;
import com.wipro.bank.loan.entity.Loan;
import com.wipro.bank.loan.mapper.LoanMapper;
import com.wipro.bank.loan.repository.LoanRepository;

/**
 * Service implementation for Loan operations
 */
@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepository loanRepo;

    /**
     * Apply loan (based on simple business rules)
     */
    @Override
    public String applyLoan(LoanDto dto) {

        // Fetch loans for customer
        List<Loan> list = loanRepo.findByCustomerId(dto.getCustomerId());

        double totalLoan = 0;
        int activeLoans = 0;

        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                totalLoan += loan.getLoanAmount();
                activeLoans++;
            }
        }

        // Business rule: total loan limit
        if (totalLoan > 500000) {
            return "Loan Rejected: High existing loans";
        }

        // Business rule: max 3 active loans
        if (activeLoans >= 3) {
            return "Loan Rejected: Too many active loans";
        }

        // Convert DTO to entity
        Loan loan = LoanMapper.toEntity(dto);
        loan.setLoanStatus("ACTIVE");

        loanRepo.save(loan);

        return "Loan Approved";
    }

    /**
     * Get full loan history
     */
    @Override
    public List<LoanDto> getLoanHistory(int customerId) {

        List<Loan> list = loanRepo.findByCustomerId(customerId);
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {
            result.add(LoanMapper.toDto(loan));
        }

        return result;
    }

    /**
     * Get total outstanding active loan
     */
    @Override
    public double getTotalOutstandingLoan(int customerId) {

        List<Loan> list = loanRepo.findByCustomerId(customerId);

        double total = 0;

        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                total += loan.getLoanAmount();
            }
        }

        return total;
    }

    /**
     * Close loan
     */
    @Override
    public String closeLoan(int loanId) {

        Loan loan = loanRepo.findById(loanId).orElse(null);

        if (loan == null) {
            return "Loan not found";
        }

        if ("CLOSED".equals(loan.getLoanStatus())) {
            return "Loan already closed";
        }

        loan.setLoanStatus("CLOSED");
        loanRepo.save(loan);

        return "Loan closed successfully";
    }

    /**
     * Get active loans only
     */
    @Override
    public List<LoanDto> getActiveLoans(int customerId) {

        List<Loan> list = loanRepo.findByCustomerId(customerId);
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {
            if ("ACTIVE".equals(loan.getLoanStatus())) {
                result.add(LoanMapper.toDto(loan));
            }
        }

        return result;
    }
}