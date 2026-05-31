package com.wipro.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	
	
	


	/**
	 * RestTemplate Bean for calling other microservices
	 */

	@Bean
	@LoadBalanced 
	public RestTemplate getRestTemplate() {
		
		
		return new RestTemplate();
		
	}
	
}
