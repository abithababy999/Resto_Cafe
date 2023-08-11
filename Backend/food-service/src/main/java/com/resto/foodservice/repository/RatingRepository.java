package com.resto.foodservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.foodservice.model.FoodItem;
import com.resto.foodservice.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long>{
	 List<Rating> findByFoodItem(FoodItem foodItem);
}
