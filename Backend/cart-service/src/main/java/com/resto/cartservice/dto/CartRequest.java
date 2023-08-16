package com.resto.cartservice.dto;

public class CartRequest {
	
	private Long FoodId;
	private Long CustomerId;
	private Integer quantity;
	
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getFoodId() {
		return FoodId;
	}
	public void setFoodId(Long foodId) {
		FoodId = foodId;
	}
	public Long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

}
