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
    public String createCustomer(CustomerDto dto) {

    	// Convert incoming DTO to Entity before saving to DB
    	Customer customer = CustomerMapper.toEntity(dto);

    	repo.save(customer);

    	return "Customer Profile Created Successfully" ;    }


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

  
}