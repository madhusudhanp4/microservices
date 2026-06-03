package com.wipro.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bank.dto.CustomerDto;
import com.wipro.bank.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    //Create customer
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE')")
    @PostMapping("/create")
    public String create(@Valid @RequestBody CustomerDto dto) {
        return service.createCustomer(dto);
    }

    // Get customer by ID
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable int id) {
        return service.getCustomerById(id);
    }

    // Get all customers
    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
    @GetMapping("/all")
    public List<CustomerDto> getAll() {
        return service.getAllCustomers();
    }
    
    
}