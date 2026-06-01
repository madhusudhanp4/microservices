package com.wipro.bank.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.bank.loan.dto.LoanDto;
import com.wipro.bank.loan.entity.Loan;
import com.wipro.bank.loan.mapper.LoanMapper;
import com.wipro.bank.loan.repository.LoanRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * Service implementation for Loan operations
 */
@Service
public class LoanServiceImpl implements ILoanService {

	@Autowired
	private LoanRepository loanRepo;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Apply loan (based on simple business rules)
	 */
	
	@Override
	@CircuitBreaker(name = "CustomerService", fallbackMethod = "customerFallback")
	public String applyLoan(LoanDto dto) {

		
		CustomerDto customer = restTemplate.getForObject("http://CUSTOMER-SERVICE/customer/" + dto.getCustomerId(), CustomerDto.class );

		if (customer == null) {
			return "Customer not found";
		}


		// Fetch loans for customer
		List<Loan> activeLoans = loanRepo.findByCustomerIdAndLoanStatus(dto.getCustomerId(),("ACTIVE"));

		// Business rule: max active loans
		if (activeLoans.size() >= 3)
			return "Loan Rejected: Too many active loans";

		double totalLoan = 0;

		// Calculate active loans and total amount
		for (Loan loan : activeLoans) {
			totalLoan += loan.getLoanAmount();
		}

		// Business rule: total loan limit
		if (totalLoan + dto.getLoanAmount() > 50000)
			return "Loan limit exceeded";


		// Convert DTO to Entity before saving
		Loan loan = LoanMapper.toEntity(dto);

		loanRepo.save(loan);

		return "Loan Approved ";
	}

	/**
	 * Get full loan history
	 */
	// Get complete loan history of customer
	@Override
	public List<LoanDto> getLoanHistory(int customerId) {

		List<Loan> loans = loanRepo.findByCustomerId(customerId);
		List<LoanDto> dtoList = new ArrayList<>();

		for (Loan loan : loans) {

			// Convert entity to DTO
			dtoList.add(LoanMapper.toDto(loan));
		}

		return dtoList;
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

		if (loan == null)
			return "Loan not found";

		// Updating existing entity status (not creating new one)
		loan.setLoanStatus("CLOSED");

		loanRepo.save(loan);

		return "Loan closed successfully ";
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

	                // Convert active entity to DTO
	                result.add(LoanMapper.toDto(loan));
	            }
	        }

	        return result;
	    }
}