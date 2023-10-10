package com.resto.orderservice.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resto.orderservice.Repository.OrderItemRepository;
import com.resto.orderservice.Repository.OrderRepository;
import com.resto.orderservice.dto.CartItem;
import com.resto.orderservice.dto.FoodItem;
import com.resto.orderservice.dto.OrderRequest;
import com.resto.orderservice.model.Order;
import com.resto.orderservice.model.OrderItem;
import com.resto.orderservice.model.OrderStatus;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository oir;
	
	public Order createOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setCustomerId(orderRequest.getCustomerId());
		List<OrderItem> orderItems=convertCartToOrder(orderRequest.getOrderItems(),order);
		order.setCartItems(orderItems);
		order.setDineIn(orderRequest.getDineIn());
		order.setPaid(orderRequest.getPaid());
		order.setTimeStamp(LocalDateTime.now());
		order.setStatus(OrderStatus.PENDING);
		order.setTotalAmount(orderRequest.getTotalAmount());
	
		order=orderRepo.save(order);
		clearCart(orderRequest.getCustomerId());
	
		return order;
		
	}
	
	public Page<Order> getOrdersOfUser(Long userId,Pageable page){
		return orderRepo.findAllByCustomerIdOrderByTimeStampDesc(userId, page);
	}
	
	public Page<Order> getOrdersForChef(Pageable page){
		return orderRepo.findAllByChefIdIsNull(page);
	}
	public Page<Order> getOrdersForChefById(Long id,Pageable page){
		return orderRepo.findAllByChefId(id,page);
	}
	
	
	
	public List<Order> getActiveOrdersForChef(Long chefId,OrderStatus status){
		return orderRepo.findAllByChefIdAndStatus(chefId, status);
	}
	
	
	public Order updateOrderStatus(Long id,OrderStatus status,Long chefId) {
		Optional<Order> temp=orderRepo.findById(id);
		if(temp.isEmpty())
			return null;
		Order order=temp.get();
		
		order.setStatus(status);
	
		order.setChefId(chefId);
		return orderRepo.save(order);
	}
	public Order findOrderById(Long id) {
		Optional<Order> temp=orderRepo.findById(id);
		if(temp.isEmpty())
			return null;
		return temp.get();
	}
	
	public List<OrderItem> getAllOrderItem(Long id){
		return oir.findByOrder_Id(id);
	}
	
	private List<OrderItem> convertCartToOrder(List<CartItem> unCheckedItems,Order order){
		return unCheckedItems.stream().map(x->{
			FoodItem foodItem=fetchFoodItem(x.getFoodId());
			if(foodItem==null)
				return null;
			OrderItem orderItem=new OrderItem();
			orderItem.setFoodId(x.getFoodId());
			orderItem.setFoodName(foodItem.getName());
			orderItem.setQuantity(x.getQuantity());
			orderItem.setImage(foodItem.getImage());
			orderItem.setOrderedPrice(foodItem.getPrice());
			orderItem.setOrder(order);
			return orderItem;
		}).toList();
		
	}
	
	private FoodItem fetchFoodItem(Long foodId) {
		
			try {
	    	 	RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<FoodItem> response =restTemplate.getForEntity("http://localhost:8089/api/foodservice/"+foodId, FoodItem.class);
				FoodItem item=response.getBody();
				if(item== null) {
					return null;
				}
				
				return item;
	    	}catch (Exception ex) {
	    		return null;
	    	}
		
	}
	
	private Boolean clearCart(Long userId) {

		try {
    	 	RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Boolean> response =restTemplate.getForEntity("http://localhost:8089/api/v1/cart/"+userId, Boolean.class);
			Boolean item=response.getBody();
			if(item== null) {
				return null;
			}
			
			return item;
    	}catch (Exception ex) {
    		return null;
    	}
	}
	
	
}
