package com.wipro.bank.loan.mapper;

import com.wipro.bank.loan.dto.LoanDto;
import com.wipro.bank.loan.entity.Loan;

public class LoanMapper {

    public static LoanDto toDto(Loan loan) {

        LoanDto dto = new LoanDto();

        dto.setLoanId(loan.getLoanId());
        dto.setLoanType(loan.getLoanType());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setLoanStatus(loan.getLoanStatus());
        dto.setCustomerId(loan.getCustomerId()); // ✅ FIXED

        return dto;
    }

    public static Loan toEntity(LoanDto dto) {

        Loan loan = new Loan();

        loan.setLoanType(dto.getLoanType());
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setInterestRate(dto.getInterestRate());
        loan.setLoanStatus("ACTIVE");

        loan.setCustomerId(dto.getCustomerId()); // ✅ FIXED

        return loan;
    }
}
