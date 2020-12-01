package com.impl.mycomposite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartItem {

	private int cartItemId;
	private int productId;
	private int quantity;
	private Product product;
	
	@JsonIgnore
    private Cart cart;

	public CartItem(int cartItemId, int productId, int quantity, Cart cart) {
		super();
		this.cartItemId = cartItemId;
		this.productId = productId;
		this.quantity = quantity;
		this.cart = cart;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", productId=" + productId + ", quantity=" + quantity + ", cart="
				+ cart + "]";
	}
}
