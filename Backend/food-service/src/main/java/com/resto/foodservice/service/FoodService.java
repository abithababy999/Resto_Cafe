package com.resto.foodservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resto.foodservice.model.FoodItem;
import com.resto.foodservice.model.Rating;
import com.resto.foodservice.repository.FoodRepository;
import com.resto.foodservice.repository.RatingRepository;

@Service
public class FoodService {
	
	@Autowired
	RatingRepository ratingRepository;
	
	public ResponseEntity<Rating> addFoodItem(Rating foodItem)
	{
		
		return ResponseEntity.ok(ratingRepository.save(foodItem));
	}

}
