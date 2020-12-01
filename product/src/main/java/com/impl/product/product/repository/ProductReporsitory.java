package com.impl.product.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impl.product.product.entity.Product;

public interface ProductReporsitory extends JpaRepository<Product, Integer>{
}
