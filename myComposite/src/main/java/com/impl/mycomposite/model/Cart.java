package com.impl.mycomposite.model;

import java.util.Set;

public class Cart {

	int cartId;
	int customerId;
	int walletId;
	private Set<CartItem> cartItems;

	public Cart(int cartId, int customerId, int walletId, Set<CartItem> cartItems) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.walletId = walletId;
		this.cartItems = cartItems;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", walletId=" + walletId + ", cartItems="
				+ cartItems + "]";
	}
	
	
	
	
}
