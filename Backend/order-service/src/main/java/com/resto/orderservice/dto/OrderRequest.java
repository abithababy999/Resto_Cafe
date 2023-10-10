package com.resto.orderservice.dto;

import java.util.List;

import com.resto.orderservice.model.OrderItem;

public class OrderRequest {
	
	private Long CustomerId;
	private List<CartItem> cartItems;
	private Boolean dineIn;
	private Boolean paid;
	private Double totalAmount;
	
	public Double getTotalAmount() {
		return totalAmount;
	}





	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}





	public OrderRequest() {
		
	}
	
	
	


	public OrderRequest(Long customerId, List<CartItem> cartItems, Boolean dineIn, Boolean paid) {
		super();
		CustomerId = customerId;
		this.cartItems = cartItems;
		this.dineIn = dineIn;
		this.paid = paid;
	}





	public List<CartItem> getCartItems() {
		return cartItems;
	}





	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}





	public Boolean getDineIn() {
		return dineIn;
	}





	public void setDineIn(Boolean dineIn) {
		this.dineIn = dineIn;
	}





	public Boolean getPaid() {
		return paid;
	}





	public void setPaid(Boolean paid) {
		this.paid = paid;
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
