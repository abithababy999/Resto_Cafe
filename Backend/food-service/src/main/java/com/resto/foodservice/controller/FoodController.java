package com.resto.foodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.foodservice.model.FoodItem;
import com.resto.foodservice.model.Rating;
import com.resto.foodservice.service.FoodService;

@RestController
@RequestMapping("api/foodservice")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/addFoodItem")
	public ResponseEntity<Rating>adminAddFoodItem(@RequestBody Rating foodItem){
		
		return foodService.addFoodItem(foodItem);
		
	}
}
