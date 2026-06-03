package com.wipro.bank.service;

import java.util.List;

import com.wipro.bank.dto.CustomerDto;

public interface ICustomerService {

	String createCustomer(CustomerDto dto);
	
    CustomerDto getCustomerById(int customerId);

    List<CustomerDto> getAllCustomers();


    
    
}
