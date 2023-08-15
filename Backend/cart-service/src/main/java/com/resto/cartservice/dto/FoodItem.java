package com.resto.cartservice.dto;

public class FoodItem {
	
	private Long foodId;
	private String name;
	private String description;
	private Boolean availability;
	private Double price;
	private String image;
	private String category;
	private String dietry;
	private Short ratingScore;
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAvailability() {
		return availability;
	}
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDietry() {
		return dietry;
	}
	public void setDietry(String dietry) {
		this.dietry = dietry;
	}
	public Short getRatingScore() {
		return ratingScore;
	}
	public void setRatingScore(Short ratingScore) {
		this.ratingScore = ratingScore;
	}
	
	
}
