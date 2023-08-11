package com.resto.inventoryservice.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.inventoryservice.dto.InventoryRequest;
import com.resto.inventoryservice.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	Page<Inventory> findAllByOrderByTimestampDesc(Pageable pageable);
	
	Page<Inventory> findAllByChefIdOrderByTimestampDesc(Long chefId,Pageable pageable);
}
