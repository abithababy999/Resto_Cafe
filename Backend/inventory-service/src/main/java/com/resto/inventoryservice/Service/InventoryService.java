package com.resto.inventoryservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resto.inventoryservice.Repository.InventoryRepository;
import com.resto.inventoryservice.dto.InventoryRequest;
import com.resto.inventoryservice.model.Inventory;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
	


}
