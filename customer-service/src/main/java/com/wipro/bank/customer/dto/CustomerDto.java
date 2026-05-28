package com.wipro.bank.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor

public class CustomerDto {

	private int customerId;

	@NotBlank(message = "Name cannot be empty")
	private String customerName;
	
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
	private String mobile;
	
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Address cannot be empty")
	private String address;


}
