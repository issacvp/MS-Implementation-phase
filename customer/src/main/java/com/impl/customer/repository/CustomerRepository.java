package com.impl.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impl.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
