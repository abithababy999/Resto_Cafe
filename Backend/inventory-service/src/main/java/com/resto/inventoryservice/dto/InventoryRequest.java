package com.resto.inventoryservice.dto;

import com.resto.inventoryservice.model.InventoryStatus;

public class InventoryRequest {
	
	
	private Long chef_id;
	private String item_name;
	private Integer quantity;
	private InventoryStatus status;
	
	public InventoryRequest() {
		
	}
	public InventoryRequest(Long chef_id, String item_name, Integer quantity, InventoryStatus status) {
		super();
		this.chef_id = chef_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.status = status;
	}
	public Long getChef_id() {
		return chef_id;
	}
	public void setChef_id(Long chef_id) {
		this.chef_id = chef_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public InventoryStatus getStatus() {
		return status;
	}
	public void setStatus(InventoryStatus status) {
		this.status = status;
	}
	
	

}
