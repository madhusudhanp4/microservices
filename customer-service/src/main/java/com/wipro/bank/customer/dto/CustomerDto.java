package com.wipro.bank.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class CustomerDto {

	private int customerId;

	@NotBlank(message = "Customer name is required")
	@Size(min = 4, message = "Customer name must be at least 4 characters")
	private String customerName;
	
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	private String mobile;
	
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Address cannot be empty")
	@Size(min = 6, message = "Customer name must be at least 4 characters")
	private String address;


}
