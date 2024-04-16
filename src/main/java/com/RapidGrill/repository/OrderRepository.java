package com.RapidGrill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RapidGrill.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(Long id);

    public List<Order> findByRestaurantId(Long id);
}
