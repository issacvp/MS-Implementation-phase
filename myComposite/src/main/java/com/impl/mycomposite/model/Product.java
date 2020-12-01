package com.impl.mycomposite.model;

public class Product {
	
	int productId;
	String productName;
	int productPrice;
	
	public Product(int productId, String productName, int productPrice) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return " { \"productId\":" + productId + ", \"productName\":" + productName + ", \"productPrice\":" + productPrice+""
				+ "}";
	}
	
}
