package com.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.entity.Cart;
import com.cart.exception.NoMatchException;
import com.cart.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepo;
	
	public Cart save(Cart cart) {
		cart = cartRepo.save(cart);
		return cart;
	}
	
	public Cart update(Cart cart) {
		return save(cart);
	}
	
	public Cart findBy(int cartId) throws NoMatchException{
		Cart cart = null;
		Optional<Cart> opt = cartRepo.findById(cartId);
		if(opt.isPresent()) {
			cart = opt.get();
			return cart;
		} else {
			throw new NoMatchException("No matching cart found");
		}
	}
	
	public List<Cart> findAll(){
		return cartRepo.findAll();
	}
	
	public List<Cart> findAllByCustomer(Integer customerId){
		return cartRepo.findByCustomerId(customerId);
	}
}
