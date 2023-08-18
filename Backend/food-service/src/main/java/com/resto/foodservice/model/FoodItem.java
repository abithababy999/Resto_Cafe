package com.resto.foodservice.model;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.UpdateTimestamp;

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
	private Boolean deleted=false;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	@Transient
	private Double ratingScore;
	@JsonIgnore
	@OneToMany(mappedBy="foodItem",cascade=CascadeType.ALL)
	private List<Rating> ratings=new ArrayList<Rating>();
	
	public FoodItem()
	{
		
	}
	
	





	public Double getRatingScore() {
		return ratingScore;
	}



	public void setRatingScore(Double ratingScore) {
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
	 
	
	
	public Boolean getDeleted() {
		return deleted;
	}







	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}







	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}







	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}







	public void addRating(Rating rating) {
		ratings.add(rating);
		rating.setFoodItem(this);
	}
	
 
}
