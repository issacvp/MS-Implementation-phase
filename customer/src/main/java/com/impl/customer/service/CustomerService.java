package com.impl.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impl.customer.entity.Customer;
import com.impl.customer.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer saveData(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> findBy(int customerId) {
        return customerRepository.findById(customerId);
    }
    
    public Customer updateCustomer(int customerId, Customer custmr) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        Customer cst = null;
        if (customer.isPresent()) {
        	cst = customer.get();
        	cst.setCustomerAddress(custmr.getCustomerAddress());
        	cst.setCustomerName(custmr.getCustomerName());
            cst = customerRepository.save(custmr);
        }
        return cst;
    }

}
