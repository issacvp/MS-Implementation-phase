package com.impl.customer.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.impl.customer.entity.Customer;
import com.impl.customer.exception.NoMatchException;
import com.impl.customer.service.CustomerService;

@RefreshScope
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Value("${test.to.check.refresh}")
	private String message;
	
	@Value("${server.port}")
	private int port;
	
	@GetMapping("/check-refresh")
	public String checkRefresh() {
		return message;
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		System.out.println("addCustomer Request getting served by port::"+port);
		Customer savedCustomer=customerService.saveData(customer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCustomer.getCustomerId()).toUri();
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
	}
	
    @GetMapping(path="/customers")
    public ResponseEntity<Object> getCustomers() {
    	System.out.println("getCustomers Request getting served by port:"+port);
    	List<Customer> list = customerService.findAll();
    	if (list.size() > 0) {
    		return new ResponseEntity<Object>(list, HttpStatus.ACCEPTED);
    	} else {
    		return new ResponseEntity<Object>("No customers found!", HttpStatus.ACCEPTED);
    	}
    }
    
    @GetMapping(path="/customers/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") int customerId) {
    	System.out.println("getCustomer Request getting served by port:"+port);
    	Optional<Customer> cst = customerService.findBy(customerId);
    	if (cst.isPresent()) {
    		return new ResponseEntity<Customer>(cst.get(), HttpStatus.OK);
    	} else {
    		throw new NoMatchException("Customer not found");
    	}
    }
    
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int customerId,@RequestBody Customer customer) {
    	System.out.println("updateCustomer Request getting served by port:"+port);
        Customer updatedCustomer=customerService.updateCustomer(customerId, customer);
        if(updatedCustomer!=null) {
        	return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	    } else {
			throw new NoMatchException("Customer not found");
		}
    }
}
