package com.resto.authservice.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resto.authservice.dto.UserRegistrationRequest;
import com.resto.authservice.model.User;
import com.resto.authservice.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public ResponseEntity<User> newUserRegister(UserRegistrationRequest userRegistration)
	{
	
		 Optional<User> opt = userRepository.findByUserName(userRegistration.getUserName());
	        if(opt.isPresent()){
	            return ResponseEntity.notFound().build();
	        }
	        else{
	            User user = new User();
               //user.setUserid(IdGenerator.generateUniqueId());
	            user.setUserName(userRegistration.getUserName());
	            user.setFirstName(userRegistration.getFirstName());
	            user.setLastName(userRegistration.getLastName());
	            user.setPhoneNumber(userRegistration.getPhoneNumber());
	            user.setPassword(getEncodedPassword(userRegistration.getPassword()));
//	            user.setPassword(userRegistration.getPassword());
	            user.setRole("ROLE_USER");
	          return ResponseEntity.ok(userRepository.save(user));
	        }
	    }
	
	public ResponseEntity<User> newChefRegister(UserRegistrationRequest userRegistration)
	{
		System.out.println(userRegistration);
	
		 Optional<User> opt = userRepository.findByUserName(userRegistration.getUserName());
	        if(opt.isPresent()){
	            return ResponseEntity.notFound().build();
	        }
	        else{
	            User user = new User();
               
	            user.setUserName(userRegistration.getUserName());
	            user.setFirstName(userRegistration.getFirstName());
	            user.setLastName(userRegistration.getLastName());
	            user.setPhoneNumber(userRegistration.getPhoneNumber());
	           user.setPassword(getEncodedPassword(userRegistration.getPassword()));
	            user.setPassword(userRegistration.getPassword());
	            user.setRole("ROLE_CHEF");
	          return ResponseEntity.ok(userRepository.save(user));
	        }
	    }
	public ResponseEntity<String> deleteChef(String userName)
	{
		Optional<User> chef=userRepository.findByUserNameAndRole(userName,"chef");
		if(!chef.isPresent())
		{
			return ResponseEntity.noContent().build();
		}
		userRepository.delete(chef.get());
		return ResponseEntity.ok("Deleted successfully");
		
	}
	
	public ResponseEntity<String> userUpdatePassword(String oldPassword,String newPassword){
		
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> userTemp= userRepository.findByUserName(userName);
		//To do userNotfound exception
		if(userTemp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		User user=userTemp.get();
		//To do password incorrect;
		 if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			 user.setPassword(getEncodedPassword(newPassword));
			 userRepository.save(user);
				
			 return ResponseEntity.ok("Password changed successfully");
	           
	        }
		 return ResponseEntity.badRequest().body("Incorrect old password");

	}
	
	
	
	   private String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	
}


