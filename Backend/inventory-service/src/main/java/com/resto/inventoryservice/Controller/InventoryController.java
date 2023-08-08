package com.resto.inventoryservice.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

	
	@GetMapping("/chef")
	@PreAuthorize("hasRole('CHEF')")
	public String createRequest() {
	return "hello  chef";
	}
}
