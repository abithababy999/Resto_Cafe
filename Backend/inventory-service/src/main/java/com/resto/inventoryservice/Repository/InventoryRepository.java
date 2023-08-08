package com.resto.inventoryservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resto.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
