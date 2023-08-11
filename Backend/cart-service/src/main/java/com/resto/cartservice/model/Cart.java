package com.resto.cartservice.model;

import javax.persistence.Column;
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
	@Column(name = "cartId")
	private Long cartId;
	@Column(name = "customerId")
	private String customerId;
	
	
	@Column(name = "foodId")
	private Long foodId;
	
	@Column(name = "foodname")
	private String foodname;
	
	@Column(name = "price")
	private Long price;

	private String status;
    private Long quantity;
	private Long total;
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Cart(Long cartId, String customerId, Long foodId, String foodname, Long price, String status, Long quantity,
			Long total) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.foodId = foodId;
		this.foodname = foodname;
		this.price = price;
		this.status = status;
		this.quantity = quantity;
		this.total = total;
	}
	
	

}
