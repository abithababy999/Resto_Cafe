package com.resto.cartservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resto.cartservice.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//	public void addBycartId(Long cartId);

	public Optional<Cart>findByCustomerIdAndFoodId(Long customerId, Long foodId);
	public List<Cart>findAllByCustomerId(Long customerId);
}
