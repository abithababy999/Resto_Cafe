package com.resto.foodservice.dto;

import com.resto.foodservice.model.FoodItem;

public class RatingRequest {

	private Short ratingScore;
	private Long foodItem;
	
	public RatingRequest() {
		
	}
	public RatingRequest(Short ratingScore, Long foodItem) {
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
	public Long getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(Long foodItem) {
		this.foodItem = foodItem;
	}
	
	
}
