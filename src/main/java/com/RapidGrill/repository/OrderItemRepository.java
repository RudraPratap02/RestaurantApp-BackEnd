package com.RapidGrill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RapidGrill.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
