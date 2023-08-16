package com.resto.foodservice.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double ratingScore;
	private LocalDateTime ratingDate;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="food_item_id")
	private FoodItem foodItem;
	public Rating()
	{
	
	}
		

	public Rating(Long id, Double ratingScore, LocalDateTime ratingDate, FoodItem foodItem) {
		super();
		this.id = id;
		this.ratingScore = ratingScore;
		this.ratingDate = ratingDate;
		this.foodItem = foodItem;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getRatingScore() {
		return ratingScore;
	}
	public void setRatingScore(Double ratingScore) {
		this.ratingScore = ratingScore;
	}
	public LocalDateTime getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(LocalDateTime ratingDate) {
		this.ratingDate = ratingDate;
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}
	

}
