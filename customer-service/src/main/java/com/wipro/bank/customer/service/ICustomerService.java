package com.wipro.bank.customer.service;

import java.util.List;

import com.wipro.bank.customer.dto.CustomerDto;

public interface ICustomerService {

	String createCustomer(CustomerDto dto);
	
    CustomerDto getCustomerById(int customerId);

    List<CustomerDto> getAllCustomers();


    
    
}
