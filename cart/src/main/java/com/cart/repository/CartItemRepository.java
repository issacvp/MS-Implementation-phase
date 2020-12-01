package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

}
