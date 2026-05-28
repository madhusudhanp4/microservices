package com.wipro.bank.customer.service;

import java.util.List;

import com.wipro.bank.customer.dto.CustomerDto;

public interface ICustomerService {

    // Register customer
    CustomerDto createCustomer(CustomerDto dto);

    // View own profile
    CustomerDto getCustomerById(int customerId);

    // Update own details
    CustomerDto updateCustomer(int customerId, CustomerDto dto);

    List<CustomerDto> getAllCustomers();
    
    //delete
    String deleteCustomer(int customerId); 
}
