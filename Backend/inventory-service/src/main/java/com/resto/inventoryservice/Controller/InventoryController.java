package com.resto.inventoryservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resto.inventoryservice.Service.InventoryService;
import com.resto.inventoryservice.dto.InventoryRequest;
import com.resto.inventoryservice.model.Inventory;
import com.resto.inventoryservice.model.InventoryStatus;

@RestController
@RequestMapping("/api/inventory/")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/add")
	
	public ResponseEntity<Inventory> createInventoryRequest(@RequestBody InventoryRequest inventoryRequest){
		
		return inventoryService.createInventoryReq(inventoryRequest);
		
	}
	
	
	@PutMapping("/update/{id}")
	public  ResponseEntity<Inventory> updateStatus(@PathVariable Long id,@RequestBody InventoryStatus status){
		return inventoryService.updateInventoryRequest(id, status);
	}
	
	@GetMapping("/all")
	public Page<Inventory> getAllInventoryRequests(Pageable pageable){
		return inventoryService.getAllInventory(pageable);
	}
	
	@GetMapping("/chef/all")
	public Page<Inventory> getAllChefInventoryRequests(@RequestParam("chefId") Long chefId,Pageable pageable){
	return inventoryService.getAllInventoryOfChef(chefId,pageable);	
	}
}
