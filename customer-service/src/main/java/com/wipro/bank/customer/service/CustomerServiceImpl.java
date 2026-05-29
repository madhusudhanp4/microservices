package com.wipro.bank.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bank.customer.dto.CustomerDto;
import com.wipro.bank.customer.entity.Customer;
import com.wipro.bank.customer.mapper.CustomerMapper;
import com.wipro.bank.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository repo;

    
    //CREATE a new customer with default ACTIVE status
    @Override
    public CustomerDto createCustomer(CustomerDto dto) {

    	// Convert incoming DTO to Entity before saving to DB
        Customer customer = CustomerMapper.toEntity(dto);

        Customer saved = repo.save(customer);

        return CustomerMapper.toDto(saved);  // Convert Entity back to DTO for response	
    }

    
    //FETCH customer by ID if not closed
    @Override
    public CustomerDto getCustomerById(int customerId) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;

        
        return CustomerMapper.toDto(c);    // Convert Entity to DTO before returning response
    }

    
    //GET all active customers
    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> list = repo.findAll();
        List<CustomerDto> result = new ArrayList<>();

        for (Customer c : list) {

            if ("CLOSED".equals(c.getStatus()))
                continue;

       
            result.add(CustomerMapper.toDto(c));   // Convert each Entity to DTO
        }

        return result;
    }

    
    //UPDATE customer details
    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto dto) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;


		/*
		 * Since this is UPDATE operation, 
		 * we modify existing entity instead of creating
		 * a new one using mapper 
		 */

        c.setCustomerName(dto.getCustomerName());
        c.setMobile(dto.getMobile());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());

        Customer updated = repo.save(c);

        return CustomerMapper.toDto(updated); // Convert updated Entity to DTO
    }

    
    //Close customer account
    @Override
    public String deleteCustomer(int customerId) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null)
            return "Customer not found";

        if ("CLOSED".equals(c.getStatus()))
            return "Customer already closed";

        c.setStatus("CLOSED");

        repo.save(c);

        return "Customer account closed successfully ";
    }
}