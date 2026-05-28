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

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {

        Customer customer = CustomerMapper.toEntity(dto);

        Customer saved = repo.save(customer);

        return CustomerMapper.toDto(saved);
    }

    @Override
    public CustomerDto getCustomerById(int customerId) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;

        return CustomerMapper.toDto(c);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> list = repo.findAll();
        List<CustomerDto> result = new ArrayList<>();

        for (Customer c : list) {

            if ("CLOSED".equals(c.getStatus()))
                continue;

            result.add(CustomerMapper.toDto(c)); // ✅ clean
        }

        return result;
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto dto) {

        Customer c = repo.findById(customerId).orElse(null);

        if (c == null || "CLOSED".equals(c.getStatus()))
            return null;

        c.setCustomerName(dto.getCustomerName());
        c.setMobile(dto.getMobile());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());

        Customer updated = repo.save(c);

        return CustomerMapper.toDto(updated);
    }

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