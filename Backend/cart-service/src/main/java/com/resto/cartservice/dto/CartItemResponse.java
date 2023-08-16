package com.resto.cartservice.dto;

public class CartItemResponse {
	
	private FoodItem foodItem;
	private Integer quantity;
	
	public CartItemResponse() {
		
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
