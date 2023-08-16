package com.resto.cartservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.cartservice.Service.CartService;
import com.resto.cartservice.dto.CartItemResponse;
import com.resto.cartservice.dto.CartRequest;
import com.resto.cartservice.dto.FoodItem;
import com.resto.cartservice.model.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
    private CartService cartServ;
	
	@PostMapping
	public ResponseEntity<List<CartItemResponse>> getItem(@RequestBody CartRequest cartRequest){
		List<Cart> carts=cartServ.addCart(cartRequest);
		
		return ResponseEntity.ok(getResponse(carts)) ;

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<CartItemResponse>> getCart(@PathVariable Long id){
		List<Cart> carts=cartServ.getAllCartItemsByUser( id);
		return ResponseEntity.ok(getResponse(carts));
	}
	
	@DeleteMapping
	public ResponseEntity<List<CartItemResponse>> DeleteItem(@RequestBody CartRequest cartRequest){
		List<Cart> carts=cartServ.removeFromCart(cartRequest);
		return ResponseEntity.ok(getResponse(carts));
	}
	@PutMapping("/")
	public ResponseEntity<List<CartItemResponse>> getCart(@RequestBody CartRequest cartRequest){
		List<Cart> carts=cartServ.updateQuantity(cartRequest);
		return ResponseEntity.ok(getResponse(carts));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> clearCart(@PathVariable Long userId){
		return ResponseEntity.ok(cartServ.clearCart(userId));
	}
	
	
	
	private List<CartItemResponse> getResponse(List<Cart> carts){
		List<CartItemResponse> cartItems=carts.stream().map(x->{
			CartItemResponse cartItemResponse =new CartItemResponse();
			FoodItem foodItem=cartServ.getFoodItem(x);
			cartItemResponse.setFoodItem(foodItem);
			cartItemResponse.setQuantity(x.getQuantity());
			return cartItemResponse;
		}).toList();
		return cartItems;
	}
	
}
