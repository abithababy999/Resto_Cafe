package com.resto.foodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.foodservice.dto.FoodItemRequest;
import com.resto.foodservice.dto.FoodItemResponse;
import com.resto.foodservice.dto.RatingRequest;
import com.resto.foodservice.model.FoodItem;
import com.resto.foodservice.model.Rating;
import com.resto.foodservice.service.FoodService;

@RestController
@RequestMapping("api/foodservice")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/addFoodItem")
	public ResponseEntity<FoodItem>adminAddFoodItem(@RequestBody FoodItemRequest foodItem){
		
		return foodService.addFoodItem(foodItem);
		
	}
	
	@DeleteMapping("/deletefooditem/{id}")
	public ResponseEntity<FoodItem> adminDeleteFoodItem(@PathVariable Long id){
		return foodService.deleteFoodItem(id);
		
	}
	
	@PutMapping("/updatefooditem/{id}")
	public ResponseEntity<FoodItem> adminUpdateItem(@RequestBody FoodItemRequest foodItemRequest,@PathVariable Long id){
		return foodService.updateFoodItem(id, foodItemRequest);
	}
	
	@PostMapping("/addrating")
	public ResponseEntity<Rating> addRating(@RequestBody RatingRequest rating){
		return foodService.addRating(rating);
	}
	
	@GetMapping("/fooItems")
	public ResponseEntity<FoodItemResponse> getAllFoodItem(Pageable pageable){
		return foodService.getAllFood(pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FoodItem> getFoodById(@PathVariable Long id){
		return foodService.getFoodByid(id);
	}
}
