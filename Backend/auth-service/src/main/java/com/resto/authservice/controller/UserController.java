package com.resto.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.authservice.dto.UserRegistrationRequest;
import com.resto.authservice.model.User;
import com.resto.authservice.service.UserService;

@RestController
@RequestMapping("/api/loginservice")
public class UserController {
	
	@Autowired
	UserService userService;
	
	  @PostMapping("/registerNewUser")
	    public ResponseEntity<User> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
	        return userService.newUserRegister(userRegistrationRequest);
	    }
}
