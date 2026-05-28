package com.wipro.bank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bank.customer.dto.CustomerDto;
import com.wipro.bank.customer.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    //Create customer
    @PostMapping("/create")
    public CustomerDto create(@Valid @RequestBody CustomerDto dto) {
        return service.createCustomer(dto);
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable int id) {
        return service.getCustomerById(id);
    }

    // Get all customers
    @GetMapping("/all")
    public List<CustomerDto> getAll() {
        return service.getAllCustomers();
    }

    // Update customer
    @PutMapping("/update/{id}")
    public CustomerDto update(@PathVariable int id,@Valid  @RequestBody CustomerDto dto) {
        return service.updateCustomer(id, dto);
    }

    // Close (delete) customer
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return service.deleteCustomer(id);
    }
}