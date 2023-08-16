package com.resto.orderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.orderservice.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
