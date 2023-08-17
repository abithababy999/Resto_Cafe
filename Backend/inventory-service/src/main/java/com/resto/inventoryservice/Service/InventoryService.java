package com.resto.inventoryservice.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resto.inventoryservice.Repository.InventoryRepository;
import com.resto.inventoryservice.dto.InventoryRequest;
import com.resto.inventoryservice.dto.UserResponse;
import com.resto.inventoryservice.model.Inventory;
import com.resto.inventoryservice.model.InventoryStatus;

@Service
public class InventoryService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private InventoryRepository inventoryRepo;
	
	
	public ResponseEntity<Inventory>createInventoryReq(InventoryRequest inventoryRequest) {

		Inventory inventory=new Inventory();
		try {
			
			ResponseEntity<UserResponse> response =restTemplate.getForEntity("http://localhost:8088/api/auth/chef/"+inventoryRequest.getChef_id(), UserResponse.class);
			if(response==null) {
				return ResponseEntity.badRequest().build();
			}
			inventory.setChef_id(inventoryRequest.getChef_id());
			inventory.setItemName(inventoryRequest.getItem_name());
			inventory.setQuantity(inventoryRequest.getQuantity());
			inventory.setStatus(InventoryStatus.PENDING);
		
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(inventoryRepo.save(inventory));
		
	}
	
	public ResponseEntity<Inventory>updateInventoryRequest(Long id,InventoryStatus status){
		Optional<Inventory>temp=inventoryRepo.findById(id);
		if(temp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Inventory inventory=temp.get();
		inventory.setStatus(status);
		return ResponseEntity.accepted().body(inventoryRepo.save(inventory));
		
	}
	
	public Page<Inventory> getAllInventory(Pageable pageable){

		return inventoryRepo.findAllByOrderByTimestampDesc(pageable);
	
		
	}
	
	public Page<Inventory> getAllInventoryOfChef(Long chefId,Pageable paging){

		return inventoryRepo.findAllByChefIdOrderByTimestampDesc(chefId,paging);
	}
	
	


}
