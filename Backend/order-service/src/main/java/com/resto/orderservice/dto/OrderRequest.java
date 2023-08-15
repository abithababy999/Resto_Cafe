package com.resto.orderservice.dto;

import java.util.List;

import com.resto.orderservice.model.OrderItem;

public class OrderRequest {
	
	private Long CustomerId;
	private List<CartItem> cartItems;
	
	public OrderRequest() {
		
	}
	
	
	public OrderRequest(Long customerId, List<CartItem> orderItems) {
		super();
		CustomerId = customerId;
		 cartItems = orderItems;
	}


	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	public List<CartItem> getOrderItems() {
		return  cartItems;
	}
	public void setOrderItems(List<CartItem> orderItems) {
		 cartItems = orderItems;
	}

	

}
