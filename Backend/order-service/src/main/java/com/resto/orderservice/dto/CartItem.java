package com.resto.orderservice.dto;

public class CartItem {
	
	private Long foodId;
	private Short quantity;
	
	public CartItem() {
		
	}
	
	
	public CartItem(Long foodId, Short quantity) {
		super();
		this.foodId = foodId;
		this.quantity = quantity;
	}


	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	
	

}
