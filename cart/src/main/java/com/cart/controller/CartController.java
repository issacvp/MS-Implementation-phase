package com.cart.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.entity.Cart;
import com.cart.entity.CartItem;
import com.cart.exception.NoMatchException;
import com.cart.exception.OnlineException;
import com.cart.service.CartItemService;
import com.cart.service.CartService;

@RefreshScope
@RestController
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	@Autowired
	CartService cartService;
	@Autowired
	CartItemService cartItemService;

	@GetMapping
	public List<Cart> getCarts() {
		return cartService.findAll();
	}

	@GetMapping("/{cartId}")
	public Cart getCart(@PathVariable Integer cartId) {
		try {
			Cart cart = cartService.findBy(cartId);
			return cart;
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart Not found");
		}
	}
	
	@GetMapping("/customer/{customerId}")
	public List<Cart> getCartsByCustomer(@PathVariable Integer customerId){
		return cartService.findAllByCustomer(customerId);
	}

	@PostMapping
	public ResponseEntity<Cart> save(@RequestBody Cart cart) {
		try {
			cart = cartService.save(cart);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OnlineException("Unknown error happened");
		}
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}

	@PutMapping("/{cartId}")
	public ResponseEntity<Cart> update(@PathVariable Integer cartId, @RequestBody Cart cart) {
		try {
			Cart crt = cartService.findBy(cartId);
			crt.setCustomerId(cart.getCustomerId());
			crt.setWalletId(cart.getWalletId());
			cart = cartService.update(crt);
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart Not found");
		}
		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{cartId}/items")
	public Set<CartItem> getItems(@PathVariable Integer cartId){
		try {
			Cart cart = cartService.findBy(cartId);
			return cart.getCartItems();
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart Not found");
		}
	}
	
	@PostMapping("/{cartId}/items")
	public ResponseEntity<Cart> addItem(@PathVariable Integer cartId, @RequestBody CartItem cartItem){
		try {
			Cart cart = cartService.findBy(cartId);
			cartItem.setCart(cart);
			cartItem = cartItemService.save(cartItem);
			cart.getCartItems().add(cartItem);
			cart = cartService.update(cart);
			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart Not found");
		}
	}
	
	@PutMapping("/{cartId}/items/{cartItemId}")
	public ResponseEntity<Cart> updateItem(@PathVariable Integer cartId, @PathVariable Integer cartItemId, @RequestBody CartItem cartItem){
		try {
			Cart cart = cartService.findBy(cartId);
			CartItem item = cartItemService.findBy(cartItemId);
			item.setQuantity(cartItem.getQuantity());
			item = cartItemService.update(item);
			cart.getCartItems().add(item);
			cart = cartService.update(cart);
			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart or CartItem Not found");
		}
	}
	
	@DeleteMapping("/{cartId}/items/{cartItemId}")
	public ResponseEntity<Cart> removeItem(@PathVariable Integer cartId, @PathVariable Integer cartItemId){
		try {
			Cart cart = cartService.findBy(cartId);
			CartItem item = cartItemService.findBy(cartItemId);
			cart.getCartItems().remove(item);
			cartItemService.delete(item);
			cart = cartService.update(cart);
			return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
		}catch (NoMatchException ex) {
			throw new NoMatchException("Cart or CartItem Not found");
		}
	}
}
