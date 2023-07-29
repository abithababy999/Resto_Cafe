package com.resto.cartservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.cartservice.Service.CartService;
import com.resto.cartservice.model.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
    private CartService cartServ;
	
	
}
