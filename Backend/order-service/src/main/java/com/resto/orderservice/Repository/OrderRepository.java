package com.resto.orderservice.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.orderservice.model.Order;
import com.resto.orderservice.model.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findAllByCustomerIdOrderByTimestampDesc(Long CustomerId,Pageable pageable);
	List<Order> findAllByChefIdAndStatus(Long chefId,OrderStatus status);
	Page<Order> findAllByChefIdIsNull(Pageable pageable);
	
}
