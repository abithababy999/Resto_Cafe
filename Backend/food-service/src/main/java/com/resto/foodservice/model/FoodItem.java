package com.resto.foodservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="food")
public class FoodItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="food_id")
	private Long foodId;
	private String name;
	private String description;

	private Boolean availability;
	private Double price;
	private String image;
	private String category;
	private String dietry;
	@Transient
	private Short ratingScore;
	@JsonIgnore
	@OneToMany(mappedBy="foodItem",cascade=CascadeType.ALL)
	private List<Rating> ratings=new ArrayList<Rating>();
	
	public FoodItem()
	{
		
	}
	
	

	public FoodItem(Long foodId, String name, String description, Boolean availability,
			Double price, String image, String category, String dietry) {
		super();
		this.foodId = foodId;
		this.name = name;
		this.description = description;
		this.ratings = ratings;
		this.availability = availability;
		this.price = price;
		this.image = image;
		this.category = category;
		this.dietry = dietry;
	}



	public Short getRatingScore() {
		return ratingScore;
	}



	public void setRatingScore(Short ratingScore) {
		this.ratingScore = ratingScore;
	}



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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
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
	 
	public void addRating(Rating rating) {
		ratings.add(rating);
		rating.setFoodItem(this);
	}
	
 
}
