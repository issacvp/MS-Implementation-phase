package com.cart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "CART_ITEM")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_item_id")
	private int cartItemId;
	@Column(name = "product_id")
	private int productId;
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "cart_id", nullable = false)
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

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", productId=" + productId + ", quantity=" + quantity + ", cart="
				+ cart + "]";
	}
}
