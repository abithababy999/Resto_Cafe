package com.resto.orderservice.dto;

import com.resto.orderservice.model.OrderStatus;

public class UpdateRequest {

	
	private OrderStatus status;
	private Long  chefId;
	
	
	
	public UpdateRequest(OrderStatus status, Long chefId) {
		super();
		this.status = status;
		this.chefId = chefId;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Long getChefId() {
		return chefId;
	}
	public void setChefId(Long chefId) {
		this.chefId = chefId;
	}
	
	
}
