package com.resto.foodservice.dto;

import com.resto.foodservice.model.FoodItem;

public class RatingRequest {

	private Short ratingScore;
	private FoodItem foodItem;
	
	public RatingRequest() {
		
	}
	public RatingRequest(Short ratingScore, FoodItem foodItem) {
		super();
		this.ratingScore = ratingScore;
		this.foodItem = foodItem;
	}
	public Short getRatingScore() {
		return ratingScore;
	}
	public void setRatingScore(Short ratingScore) {
		this.ratingScore = ratingScore;
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}
	
	
}
