package com.resto.cartservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.cartservice.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//	public void addBycartId(Long cartId);
	 public void deleteByCartidAndUserid(Long cartId, Long customerId);
	 public Optional<Cart> findByFoodId(Long foodId);
	 public List<Cart> findByCustomerId(Long customerId);
	 public void deleteByCustomerId(Long customerId);
	 public List<Cart> getByStatus();
	 public void deleteByCartid(Long cartId);
}
