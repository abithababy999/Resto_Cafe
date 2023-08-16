package com.resto.foodservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resto.foodservice.model.FoodItem;


@Repository
public interface FoodRepository extends JpaRepository<FoodItem,Long> {
	Page<FoodItem> findAllByAvailability(Boolean availability);
	Page<FoodItem> findAllByCategory(Pageable page,String category);
	 @Query("SELECT f FROM FoodItem f " +
	           "WHERE (:category is null or f.category = :category) " + "AND (:dietry is null or f.dietry = :dietry)"+
	           "AND (:searchTerm is null or lower(f.name) like %:searchTerm%)")
	    List<FoodItem> findByFilters(@Param("category") String category,@Param("dietry") String dietry, @Param("searchTerm") String searchTerm);
}
