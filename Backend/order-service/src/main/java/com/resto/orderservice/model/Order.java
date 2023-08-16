package com.resto.orderservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "R_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long customerId;
	
	private Long chefId;
	
	private LocalDateTime timeStamp;
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> cartItems;
	
	@Enumerated(EnumType.STRING)
    private OrderStatus status;
	
	private Double totalAmount;
	
	private Boolean paid;
	
	private Boolean DineIn;
	
	public Order () {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getChefId() {
		return chefId;
	}
	public void setChefId(Long chefId) {
		this.chefId = chefId;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public Boolean getDineIn() {
		return DineIn;
	}
	public void setDineIn(Boolean dineIn) {
		DineIn = dineIn;
	}
	public List<OrderItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<OrderItem> cartItems) {
		this.cartItems = cartItems;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
