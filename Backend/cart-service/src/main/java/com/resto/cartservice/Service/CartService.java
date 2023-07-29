package com.resto.cartservice.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resto.cartservice.Repository.CartRepository;
import com.resto.cartservice.model.Cart;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepo;

	 @Transactional
	 public Cart addCart(Cart c)
	 {
		 Cart exist = null;
	     Optional<Cart> opt = cartRepo.findByFoodId(c.getFoodId());
	     if (opt.isPresent())
	     {
	    	 exist = opt.get();
	         exist.setQuantity(exist.getQuantity() + 1);
	         exist.setTotal(exist.getQuantity() * c.getPrice());
	         return cartRepo.save(exist);
	     }
	     else
	     {
	         c.setStatus("In progress");
	         c.setQuantity(1L);
	         c.setTotal(c.getQuantity() * c.getPrice());
	         Cart saveCart = cartRepo.save(c);
	         System.out.println("added");
	         return saveCart;
	     }
	 }
	 @Transactional
	    public void deleteCartByCartIdAndUserId(Long cartid, Long customerId) {
	        cartRepo.deleteByCartidAndUserid(cartid, customerId);
	        System.out.println("deleted");
	    }
	 
	 
	 public Cart iupdateQuantity(Long cartId) {
	        Cart c = cartRepo.findById(cartId).orElse(null);
	        if(c!=null){
	            c.setQuantity(c.getQuantity() + 1);
	            c.setTotal(c.getQuantity() * c.getPrice());
	        }
	        cartRepo.save(c);
	        return c;
	    }
	 
	 public Cart updateQuantity(Long cartId) {
	        Cart c = cartRepo.findById(cartId).orElse(null);
	        if(c!=null){
	            if(c.getQuantity()>1) {
	                c.setQuantity(c.getQuantity() - 1);
	                c.setTotal(c.getQuantity() * c.getPrice());
	            }
	        }
	        cartRepo.save(c);
	        return c;
	    }

	    

//	    public Cart updateQuan(Cart cart) {
//	        Cart exist = null;
//	        Optional<Cart> existing = cartRepo.findByProdid(cart.getFoodId());
//	        if(existing.isPresent()){
//	            exist = existing.get();
//	            exist.setQuantity(cart.getQuantity()+1);
//	        }
//	        return cartRepo.save(exist);
//	    }


	    public Long getTotalAmount(Long cartId) {
	        Cart c = null;
	        Optional<Cart> opt = cartRepo.findById(cartId);
	        if(opt.isPresent()){
	            c = opt.get();
	        }
	        return c.getTotal();
	    }

	    public Cart updateStatus(Long cartId) {
	        Cart c = null;
	        Optional<Cart> opt = cartRepo.findByFoodId(cartId);
	        if(opt.isPresent()){
	            c = opt.get();
	            c.setStatus("Ready");
	        }
	        return cartRepo.save(c);
	    }

	    public List<Cart> getAllOrders() {
	        return cartRepo.findAll();
	    }

	    public List<Cart> getByStatus() {
	        return cartRepo.getByStatus();
	    }

	    public void deleteByUserId(Long customerId) {
	        cartRepo.deleteByCustomerId(customerId);
	    }

	    public void deleteByCartId(Long cartId) {
	        cartRepo.deleteByCartid(cartId);
	    }


}
