package com.resto.inventoryservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String itemName;
	private Integer quantity;
	private Long chefId;
	private InventoryStatus status;
	@CreatedDate
	private LocalDateTime timestamp;
	public Inventory() {
		
	}
	
	


	public Inventory(Long id, String itemName, Integer quantity, Long chef_id, InventoryStatus status,
			LocalDateTime timestamp) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.chefId = chef_id;
		this.status = status;
		this.timestamp = timestamp;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getChef_id() {
		return chefId;
	}
	public void setChef_id(Long chef_id) {
		this.chefId = chef_id;
	}
	public InventoryStatus getStatus() {
		return status;
	}
	public void setStatus(InventoryStatus status) {
		this.status = status;
	}
	
	


}
