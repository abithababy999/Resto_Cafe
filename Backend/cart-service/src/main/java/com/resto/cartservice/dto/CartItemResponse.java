package com.resto.cartservice.dto;

public class CartItemResponse {
	
	private FoodItem foodItem;
	private Short quantity;
	
	public CartItemResponse() {
		
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	
	

}
