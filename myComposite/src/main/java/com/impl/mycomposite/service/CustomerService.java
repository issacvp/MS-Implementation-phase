package com.impl.mycomposite.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.impl.mycomposite.model.Customer;

@Service
@FeignClient(name="CUSTOMER-MICROSERVICE")
public interface CustomerService {
	
	@GetMapping("/customers")
    public String getCustomers();    
    
	@GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") int customerId);
    
    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer);
    
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") int customerId,@RequestBody Customer customer);
}
