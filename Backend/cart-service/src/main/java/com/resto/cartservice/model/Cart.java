package com.resto.cartservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	private Long customerId;
	
	private Long foodId;
	
    private Integer quantity;
    
    
    public Cart() {
    	
    }
	
	public Cart(Long cartId, Long customerId, Long foodId, Integer quantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.foodId = foodId;
		
		this.quantity = quantity;
	}
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	

}
