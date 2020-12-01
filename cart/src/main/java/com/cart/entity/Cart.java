package com.cart.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	int cartId;
	
	@Column(name = "customer_id")
	int customerId;
	
	@Column(name = "wallet_id")
	int walletId;
	
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
