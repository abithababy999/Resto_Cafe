package com.resto.orderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.orderservice.Service.OrderService;
import com.resto.orderservice.dto.OrderRequest;
import com.resto.orderservice.dto.UpdateRequest;
import com.resto.orderservice.model.Order;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService os;
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest){
		return ResponseEntity.ok(os.createOrder(orderRequest));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> showOrder(@PathVariable Long id){
		return ResponseEntity.ok(os.findOrderById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateStatus(@PathVariable Long id,@RequestBody UpdateRequest updateRequest){
		return ResponseEntity.ok(os.updateOrderStatus(id, updateRequest.getStatus(), updateRequest.getChefId()));
	}
	
}
