package com.wipro.bank.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.loan.dto.LoanDto;
import com.wipro.bank.loan.entity.Loan;
import com.wipro.bank.loan.mapper.LoanMapper;
import com.wipro.bank.loan.repository.LoanRepository;

@Service
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Override
    public String processLoan(LoanDto dto) {

        List<Loan> list = loanRepo.findAll();

        double totalLoan = 0;
        int activeLoans = 0;

        for (Loan loan : list) {

            if (loan.getCustomerId() == dto.getCustomerId() &&
                "ACTIVE".equals(loan.getLoanStatus())) {

                totalLoan += loan.getLoanAmount();
                activeLoans++;
            }
        }

        if (totalLoan > 500000)
            return "Loan Rejected: High existing loans";

        if (activeLoans >= 3)
            return "Loan Rejected: Too many active loans";

        // ✅ use mapper (no customer object)
        Loan loan = LoanMapper.toEntity(dto);

        loanRepo.save(loan);

        return "Loan Approved ";
    }

    @Override
    public List<LoanDto> getCustomerLoans(int customerId) {

        List<Loan> list = loanRepo.findAll();
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            if (loan.getCustomerId() == customerId) {
                result.add(LoanMapper.toDto(loan));
            }
        }

        return result;
    }

    @Override
    public double getTotalActiveLoanAmount(int customerId) {

        List<Loan> list = loanRepo.findAll();

        double total = 0;

        for (Loan loan : list) {

            if (loan.getCustomerId() == customerId &&
                "ACTIVE".equals(loan.getLoanStatus())) {

                total += loan.getLoanAmount();
            }
        }

        return total;
    }

    @Override
    public String closeLoan(int loanId) {

        Loan loan = loanRepo.findById(loanId).orElse(null);

        if (loan == null)
            return "Loan not found";

        if ("CLOSED".equals(loan.getLoanStatus()))
            return "Loan already closed";

        loan.setLoanStatus("CLOSED");

        loanRepo.save(loan);

        return "Loan closed successfully ";
    }

    @Override
    public List<LoanDto> getActiveLoans(int customerId) {

        List<Loan> list = loanRepo.findAll();
        List<LoanDto> result = new ArrayList<>();

        for (Loan loan : list) {

            if (loan.getCustomerId() == customerId &&
                "ACTIVE".equals(loan.getLoanStatus())) {

                result.add(LoanMapper.toDto(loan));
            }
        }

        return result;
    }
}