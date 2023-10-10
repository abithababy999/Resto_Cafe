package com.resto.orderservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.resto.orderservice.model.OrderItem;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {
	
	@Autowired
	private OrderService os;
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
	    try {
	        Order createdOrder = os.createOrder(orderRequest);
	        return ResponseEntity.ok(createdOrder);
	    } catch (Exception e) {
	        // Handle the exception and return an error response
	        String errorMessage = "Failed to create the order. Reason: " + e.getMessage();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}

	@GetMapping("/{id}/cartitems")
	@PreAuthorize("hasRole('USER') or hasRole('CHEF')")
	public ResponseEntity<List<OrderItem>> showOrder(@PathVariable Long id){
	    return ResponseEntity.ok(os.getAllOrderItem(id));
	}

	
	@GetMapping("/all")
	@PreAuthorize("hasRole('CHEF')")
    public Page<Order> getOrdersForChef(Pageable page) {
        return os.getOrdersForChef(page);
    }
	
	@GetMapping("/{id}/all")
	@PreAuthorize("hasRole('USER')")
    public Page<Order> getOrdersForUser(@PathVariable Long id,Pageable page) {
        return os.getOrdersOfUser(id, page);
    }
	@GetMapping("/chef/{id}")
	@PreAuthorize("hasRole('CHEF')")
	public Page<Order> getOrderForChefById(@PathVariable Long id,Pageable page){
		return os.getOrdersForChefById(id,page);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateStatus(@PathVariable Long id,@RequestBody UpdateRequest updateRequest){
		return ResponseEntity.ok(os.updateOrderStatus(id, updateRequest.getStatus(), updateRequest.getChefId()));
	}
	
}
