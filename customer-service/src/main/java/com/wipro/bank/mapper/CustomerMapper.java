package com.wipro.bank.mapper;

import com.wipro.bank.dto.CustomerDto;
import com.wipro.bank.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toDto(Customer c) {

        CustomerDto dto = new CustomerDto();

        dto.setCustomerName(c.getCustomerName());
        dto.setMobile(c.getMobile());
        dto.setEmail(c.getEmail());
        dto.setAddress(c.getAddress());
        dto.setStatus(c.getStatus());

        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {

        Customer c = new Customer();

        c.setCustomerName(dto.getCustomerName());
        c.setMobile(dto.getMobile());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());
        c.setStatus(dto.getStatus());

        return c;
    }
}