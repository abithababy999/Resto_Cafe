package com.resto.inventoryservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String itemName;
	private Integer quantity;
	private Long chef_id;
	private InventoryStatus status;
	
	public Inventory() {
		
	}
	
	
	public Inventory(Long id, String itemName, Integer quantity, Long chef_id, InventoryStatus status) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.chef_id = chef_id;
		this.status = status;
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
		return chef_id;
	}
	public void setChef_id(Long chef_id) {
		this.chef_id = chef_id;
	}
	public InventoryStatus getStatus() {
		return status;
	}
	public void setStatus(InventoryStatus status) {
		this.status = status;
	}
	
	


}
