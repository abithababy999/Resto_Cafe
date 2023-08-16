package com.resto.foodservice.dto;

import com.resto.foodservice.model.FoodItem;

public class RatingRequest {

	private Double ratingScore;
	private Long foodItem;
	
	public RatingRequest() {
		
	}
	public RatingRequest(Double ratingScore, Long foodItem) {
		super();
		this.ratingScore = ratingScore;
		this.foodItem = foodItem;
	}
	public Double getRatingScore() {
		return ratingScore;
	}
	public void setRatingScore(Double ratingScore) {
		this.ratingScore = ratingScore;
	}
	public Long getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(Long foodItem) {
		this.foodItem = foodItem;
	}
	
	
}
