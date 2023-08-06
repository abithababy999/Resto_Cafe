package com.resto.authservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.authservice.dto.JwtRequest;
import com.resto.authservice.dto.JwtResponse;
import com.resto.authservice.dto.PasswordRequest;
import com.resto.authservice.dto.UserRegistrationRequest;
import com.resto.authservice.model.User;
import com.resto.authservice.service.JwtService;
import com.resto.authservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	
	  @PostMapping("/registerNewUser")
	    public ResponseEntity<User> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
	        return userService.newUserRegister(userRegistrationRequest);
	    }
	  
	  @PreAuthorize("hasRole('admin')")
	  @PostMapping("/registerNewChef")
	    public ResponseEntity<User> registerNewChef(@RequestBody UserRegistrationRequest userRegistrationRequest) {
	       logger.debug("created user");
		  return userService.newChefRegister(userRegistrationRequest);
	    }
	  
	  @PreAuthorize("hasRole('admin')")
	  @DeleteMapping("/deleteChef/{userName}")
	  public ResponseEntity<String> removeChef(@PathVariable String userName)
	  {
		  return userService.deleteChef(userName);
	  }
	  

	  @PutMapping("/changePassword")
	  @PreAuthorize("hasAnyRole('user','chef')")
	  public ResponseEntity<String> updateUserPassword(@RequestBody PasswordRequest passwordRequest ){
		  return userService.userUpdatePassword(passwordRequest.getOldPassword(),passwordRequest.getNewPassword() );
	  }
	  
	  @PostMapping("/authenticate")
	  public JwtResponse createJwtToken(@RequestBody  JwtRequest jwtRequest) throws Exception {
		logger.debug("IN authenticate", jwtRequest);
		  return jwtService.createJwtToken(jwtRequest);
	    }
}
