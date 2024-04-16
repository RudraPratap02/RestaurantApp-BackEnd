package com.RapidGrill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RapidGrill.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByCustomerId(Long userId);
}
