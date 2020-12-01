package com.impl.mycomposite.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impl.mycomposite.model.Cart;
import com.impl.mycomposite.model.CartItem;
import com.impl.mycomposite.model.Product;
import com.impl.mycomposite.service.CartService;
import com.impl.mycomposite.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/carts")
public class CartCompositeController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService prdService;
	
	//@GetMapping(path="/{cartId}")
    @HystrixCommand(fallbackMethod = "getByCartId")
    public Cart getCart(@PathVariable("cartId") int cartId) {
		Cart cart = cartService.getCart(cartId);
		Set<CartItem> items = cart.getCartItems();
		items.stream().forEach(obj -> {
			Product product = prdService.getProduct(obj.getProductId());
			obj.setProduct(product);
		});
    	return cart;
    }
	
	@SuppressWarnings("unused")
    private String getByCartId(@PathVariable("customerId") int cartId) { 
        return new String ("CIRCUIT BREAKER ENABLED!!! No Response From cart Service  or product service at this moment. " +
                    " Service will be back shortly - ");
    }
}
