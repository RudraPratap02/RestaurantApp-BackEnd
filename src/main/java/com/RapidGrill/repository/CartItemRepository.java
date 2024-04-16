package com.RapidGrill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RapidGrill.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
