package com.wipro.bank.account.controller;

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

import com.wipro.bank.account.dto.AccountDto;
import com.wipro.bank.account.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private IAccountService service;

	//  Create account
	@PostMapping("/create")
	public String create(@Valid @RequestBody AccountDto dto) {
		return service.createAccount(dto);
	}

	//  Get account
	@GetMapping("/{accountNumber}")
	public AccountDto getAccount(@PathVariable String accountNumber) {
		return service.getAccount(accountNumber);
	}

	//  Get all accounts
	@GetMapping("/all")
	public List<AccountDto> getAll() {
		return service.getAllAccounts();
	}

	//  Check balance
	@GetMapping("/balance/{accountNumber}")
	public double getBalance(@PathVariable String accountNumber) {
		return service.getBalance(accountNumber);
	}
	

}