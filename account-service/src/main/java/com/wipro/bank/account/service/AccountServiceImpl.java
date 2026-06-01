package com.wipro.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.bank.account.dto.AccountDto;
import com.wipro.bank.account.entity.Account;
import com.wipro.bank.account.mapper.AccountMapper;
import com.wipro.bank.account.repository.AccountRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/*
 * Service implementation for Account operations.
 * Handles business logic related to accounts.
 */

@Service
public class AccountServiceImpl implements IAccountService {


	
    @Autowired
    private AccountRepository accountRepo;
    
    
	@Autowired 
	private RestTemplate restTemplate;
	
    
    /**
     * Create a new account for an existing customer.
     * (Here we assume customerId is valid — no external call yet)
     */
    
    
	//CIRCUIT BREAKER 
    @Override
    @CircuitBreaker(name = "customerService", fallbackMethod="customerFallback")
    public String createAccount(AccountDto dto) {

    	
    	CustomerDto customer = restTemplate.getForObject("http://CUSTOMER-SERVICE/customer/" + dto.getCustomerId(),CustomerDto.class);
    	
    	 

    	 // Validate customer exists
    	 if (customer == null) {
    		 return null;  
    	 }

    	     	 
          // Convert DTO to Entity
        Account account = AccountMapper.toEntity(dto);
        long count = accountRepo.count();
        account.setAccountNumber("ACC" + (1000 + count + 1));

        accountRepo.save(account);
        
        return "Account Created Successfully";
    }
    
    
    public String customerFallback(AccountDto dto, Exception e) {
    	
    	return "Customer Service is Down";
    }
    
    

    /**
     * Fetch account details using account number
     */
    
    @Override
    public AccountDto getAccount(String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return null;
        }

        return AccountMapper.toDto(account);
    }

    /**
     * Fetch all active accounts
     */
    
    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepo.findAll();
        List<AccountDto> dtoList = new ArrayList<>();

        for (Account account : accounts) {

            if (!"CLOSED".equals(account.getStatus())) {
                dtoList.add(AccountMapper.toDto(account));
            }
        }

        return dtoList;
    }

    /**
     * Get current balance of an account
     */
    @Override
    public double getBalance(String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            return 0;
        }

        return account.getBalance();
    }

 

   
}
