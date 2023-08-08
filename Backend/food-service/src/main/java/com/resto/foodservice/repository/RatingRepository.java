package com.resto.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.foodservice.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long>{

}
