package com.resto.foodservice.dto;

public class FoodItemRequest {
	private String name;
	private String description;

	private Boolean availability;
	private Double price;
	private String image;
	private String category;
	private String dietry;
	
	public FoodItemRequest() {
		
	}
	
	
	
	public FoodItemRequest(String name, String description, Boolean availability, Double price, String image,
			String category, String dietry) {
		super();
		this.name = name;
		this.description = description;
		this.availability = availability;
		this.price = price;
		this.image = image;
		this.category = category;
		this.dietry = dietry;
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
	
}
