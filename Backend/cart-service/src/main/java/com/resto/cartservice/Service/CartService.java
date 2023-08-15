package com.resto.cartservice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resto.cartservice.Repository.CartRepository;
import com.resto.cartservice.dto.CartItemResponse;
import com.resto.cartservice.dto.CartRequest;
import com.resto.cartservice.dto.FoodItem;
import com.resto.cartservice.model.Cart;


@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepo;
	
	
	public List<Cart> getAllCartItemsByUser(Long id){
		return cartRepo.findAllByCustomerId(id);
	}
	
	public List<Cart>addCart(CartRequest cartRequest){
		Optional<Cart> temp=cartRepo.findByCustomerIdAndFoodId(cartRequest.getCustomerId(),cartRequest.getFoodId());
		if(temp.isEmpty()) {
			Cart item =new Cart();
			item.setFoodId(cartRequest.getFoodId());
			item.setCustomerId(cartRequest.getCustomerId());
			item.setQuantity((short)1);
			cartRepo.save(item);
		}else {
			Cart item=temp.get();
			item.setQuantity((short) (item.getQuantity()+1));
			
		}
		return getAllCartItemsByUser(cartRequest.getCustomerId());
	
	}
	
	public List<Cart> removeFromCart(CartRequest cartRequest){
		Optional<Cart> temp=cartRepo.findByCustomerIdAndFoodId(cartRequest.getCustomerId(),cartRequest.getFoodId());
		if(temp.isPresent()) {
			Cart item=temp.get();
			cartRepo.delete(item);
		}
		
		return getAllCartItemsByUser(cartRequest.getCustomerId());
	}
	
	public List<Cart> updateQuantity(CartRequest cartRequest){
		Optional<Cart> temp=cartRepo.findByCustomerIdAndFoodId(cartRequest.getCustomerId(),cartRequest.getFoodId());
		if(temp.isPresent()) {
			Cart item=temp.get();
			item.setQuantity(cartRequest.getQuantity());
			cartRepo.save(item);
			
		}
		

		return getAllCartItemsByUser(cartRequest.getCustomerId());
		
	}
	
	


	    
	public FoodItem getFoodItem(Cart cart) {
	    	try {
	    	 	RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<FoodItem> response =restTemplate.getForEntity("http://localhost:8089/api/foodservice/"+cart.getFoodId(), FoodItem.class);
				FoodItem item=response.getBody();
				if(item== null) {
					return null;
				}
				
				return item;
	    	}catch (Exception ex) {
	    		return null;
	    	}
	   
	    }


}
