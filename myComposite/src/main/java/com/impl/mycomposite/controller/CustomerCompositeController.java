package com.impl.mycomposite.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impl.mycomposite.model.Cart;
import com.impl.mycomposite.model.Customer;
import com.impl.mycomposite.model.Wallet;
import com.impl.mycomposite.service.CartService;
import com.impl.mycomposite.service.CustomerService;
import com.impl.mycomposite.service.WalletService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CustomerCompositeController {
	@Autowired
	CustomerService customerService;

	@Autowired
	WalletService walletService;

	@Autowired
	CartService cartService;

	@GetMapping("/customers")
	@HystrixCommand(fallbackMethod = "customerGetCustomer", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")

	}, threadPoolKey = "customerServiceAppPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "2") })
	public String getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customers/{customerId}")
	@HystrixCommand(fallbackMethod = "customerGetCustomerById")
	public Customer getCustomer(@PathVariable("customerId") int customerId) throws JsonProcessingException {
		Customer customer = customerService.getCustomer(customerId).getBody();
		List<Wallet> wallets = walletService.getWalletsByCustomer(customerId);
		List<Cart> carts = cartService.getCartsByCustomer(customerId);
		customer.setCarts(carts);
		customer.setWallets(wallets);
		return customer;
	}

	@PostMapping("/addCustomer")
	@HystrixCommand(fallbackMethod = "customerAddCustomers", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")

	}, threadPoolKey = "customerServiceAppPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "2") })
	public String addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping("/customers/{customerId}")
	@HystrixCommand(fallbackMethod = "customerUpdateById")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}

	@GetMapping("/customers/{customerId}/wallet")
	@HystrixCommand(fallbackMethod = "getCustomerWalletFallBack")
	public String getCustomerWallet(@PathVariable("customerId") int customerId) {
		return walletService.getWalletsByCustomer(customerId).toString();
	}

	@SuppressWarnings("unused")
	private String getCustomerWalletFallBack() {
		return new String("CIRCUIT BREAKER ENABLED!!! No Response From customer wallet Service at this moment. "
				+ " Service will be back shortly - " + new Date());
	}

	@GetMapping("/customers/{customerId}/cart")
	@HystrixCommand(fallbackMethod = "getCustomerCartFallBack")
	public String getCustomerCart(@PathVariable("customerId") int customerId) {
		return cartService.getCartsByCustomer(customerId).toString();
	}

	@SuppressWarnings("unused")
	private String getCustomerCartFallBack() {
		return new String("CIRCUIT BREAKER ENABLED!!! No Response From customer cart Service at this moment. "
				+ " Service will be back shortly - " + new Date());
	}

	@SuppressWarnings("unused")
	private String customerAddCustomers(@RequestBody Customer customer) {
		return new String("CIRCUIT BREAKER ENABLED!!! No Response From customer Service at this moment. "
				+ " Service will be back shortly - " + new Date());
	}

	@SuppressWarnings("unused")
	private String customerGetCustomer() {
		return new String("CIRCUIT BREAKER ENABLED!!! No Response From customer Service at this moment. "
				+ " Service will be back shortly - " + new Date());
	}

	@SuppressWarnings("unused")
	private Customer customerGetCustomerById(@PathVariable("customerId") int customerId) {
		return null;
	}

	@SuppressWarnings("unused")
	private String customerUpdateById(@PathVariable("customerId") int customerId) {
		return new String("CIRCUIT BREAKER ENABLED!!! No Response From customer Service at this moment. "
				+ " Service will be back shortly - " + new Date());
	}
}
