package com.cart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.entity.CartItem;
import com.cart.exception.NoMatchException;
import com.cart.repository.CartItemRepository;

@Service
public class CartItemService {

	@Autowired
	CartItemRepository cartItemRepo;
	
	public CartItem save(CartItem cartItem) {
		cartItem = cartItemRepo.save(cartItem);
		return cartItem;
	}
	
	public CartItem update(CartItem cartItem) {
		return save(cartItem);
	}
	
	public void delete(CartItem cartItem) {
		cartItemRepo.delete(cartItem);
	}
	
	public CartItem findBy(int cartItemId) throws NoMatchException{
		CartItem cartItem = null;
		Optional<CartItem> opt = cartItemRepo.findById(cartItemId);
		if(opt.isPresent()) {
			cartItem = opt.get();
			return cartItem;
		} else {
			throw new NoMatchException("No matching cartItem found");
		}
	}
}
