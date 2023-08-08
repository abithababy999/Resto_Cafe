package com.resto.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.foodservice.model.FoodItem;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem,Long> {

}
