package com.resto.foodservice.dto;

import java.util.List;

import com.resto.foodservice.model.FoodItem;

public class FoodItemResponse {
	
	private List<FoodItem> fooditems;
	private Integer currentPage;
	private Integer totalPages;
	private Long totalElements;
	
	public FoodItemResponse() {
		
	}
	

	public FoodItemResponse(List<FoodItem> fooditems, Integer currentPage, Integer totalPages, Long totalElements) {
		super();
		this.fooditems = fooditems;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}


	public List<FoodItem> getFooditems() {
		return fooditems;
	}

	public void setFooditems(List<FoodItem> fooditems) {
		this.fooditems = fooditems;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int i) {
		this.totalPages = i;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	
	
	
	
	

}
