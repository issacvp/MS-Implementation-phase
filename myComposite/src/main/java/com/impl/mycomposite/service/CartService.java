package com.impl.mycomposite.service;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.impl.mycomposite.model.Cart;
import com.impl.mycomposite.model.CartItem;

@Service
@FeignClient(name="CART-MICROSERVICE")
public interface CartService {

	@GetMapping
    public List<Cart> getCarts();    
	
	@GetMapping("/{cartId}")
	public Cart getCart(@PathVariable Integer cartId);
	
	@GetMapping("/customer/{customerId}")
	public List<Cart> getCartsByCustomer(@PathVariable Integer customerId);
	
	@PostMapping
	public ResponseEntity<Cart> save(@RequestBody Cart cart);
	
	@PutMapping("/{cartId}")
	public ResponseEntity<Cart> update(@PathVariable Integer cartId, @RequestBody Cart cart);
	
	@GetMapping("/{cartId}/items")
	public Set<CartItem> getItems(@PathVariable Integer cartId);
	
	@PostMapping("/{cartId}/items")
	public ResponseEntity<Cart> addItem(@PathVariable Integer cartId, @RequestBody CartItem cartItem);
	
	@PutMapping("/{cartId}/items/{cartItemId}")
	public ResponseEntity<Cart> updateItem(@PathVariable Integer cartId, @PathVariable Integer cartItemId, @RequestBody CartItem cartItem);
	
	@DeleteMapping("/{cartId}/items/{cartItemId}")
	public ResponseEntity<Cart> removeItem(@PathVariable Integer cartId, @PathVariable Integer cartItemId);
	
}	
