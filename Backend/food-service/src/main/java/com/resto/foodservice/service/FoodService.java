package com.resto.foodservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resto.foodservice.dto.FoodItemRequest;
import com.resto.foodservice.dto.FoodItemResponse;
import com.resto.foodservice.dto.RatingRequest;
import com.resto.foodservice.model.FoodItem;
import com.resto.foodservice.model.Rating;
import com.resto.foodservice.repository.FoodRepository;
import com.resto.foodservice.repository.RatingRepository;

@Service
public class FoodService {
	
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired 
	private FoodRepository foodRepository;
	
	public ResponseEntity<FoodItem> addFoodItem(FoodItemRequest foodItemRequest)
	{
		FoodItem foodItem=new FoodItem();
		foodItem.setName(foodItemRequest.getName());
		foodItem.setDescription(foodItemRequest.getDescription());
		foodItem.setAvailability(foodItemRequest.getAvailability());
		foodItem.setCategory(foodItemRequest.getCategory());
		foodItem.setDietry(foodItemRequest.getDietry());
		foodItem.setImage(foodItemRequest.getImage());
		foodItem.setRatings(new ArrayList<Rating>());
		
		return ResponseEntity.ok(foodRepository.save(foodItem));
	}
	
	public ResponseEntity<FoodItem> deleteFoodItem(Long id){
		Optional<FoodItem> temp=foodRepository.findById(id);
		if(temp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		foodRepository.delete(temp.get());
	
		return ResponseEntity.ok(temp.get());
		
	}
	
	public ResponseEntity<FoodItem> updateFoodItem(Long id,FoodItemRequest foodItemRequest)
	{
		Optional<FoodItem> temp=foodRepository.findById(id);
		if(temp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		FoodItem foodItem=temp.get();
		foodItem.setName(foodItemRequest.getName());
		foodItem.setDescription(foodItemRequest.getDescription());
		foodItem.setAvailability(foodItemRequest.getAvailability());
		foodItem.setCategory(foodItemRequest.getCategory());
		foodItem.setDietry(foodItemRequest.getDietry());
		foodItem.setImage(foodItemRequest.getImage());
	
		
		return ResponseEntity.ok(foodRepository.save(foodItem));
	}
	
	public ResponseEntity<Rating> addRating(RatingRequest ratingRequest){
		Rating rating =new Rating();
		rating.setRatingScore(ratingRequest.getRatingScore());
		rating.setFoodItem(ratingRequest.getFoodItem());
		rating.setRatingDate(LocalDateTime.now());
		return ResponseEntity.ok(ratingRepository.save(rating));
		
	}
	
	public ResponseEntity<FoodItemResponse> getAllFood(Pageable page){
		FoodItemResponse foodItemResponse=new FoodItemResponse();
		Page<FoodItem>pagedFoodItems=foodRepository.findAll(page);

		
		List<FoodItem>foodItems=((Streamable<FoodItem>) pagedFoodItems.getContent().stream().map(x->setRatingScore(x))).toList();
		
	
		foodItemResponse.setFooditems(foodItems);
		foodItemResponse.setTotalElements(pagedFoodItems.getTotalElements());
		foodItemResponse.setTotalPages(pagedFoodItems.getTotalPages());
	
		return ResponseEntity.ok(foodItemResponse);
		
	}
	
	private FoodItem setRatingScore(FoodItem foodItem){
		Short average=(short)foodItem.getRatings().stream().mapToDouble(Rating::getRatingScore).average().orElse(0.0);
		foodItem.setRatingScore(average);
		return foodItem;
		
	}
	

}
