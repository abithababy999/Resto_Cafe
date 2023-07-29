package com.resto.authservice.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resto.authservice.dto.UserRegistrationRequest;
import com.resto.authservice.model.User;
import com.resto.authservice.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	
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
	            //user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));
	            user.setPassword(userRegistration.getPassword());
	            user.setRole("user");
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
               //user.setUserid(IdGenerator.generateUniqueId());
	            user.setUserName(userRegistration.getUserName());
	            user.setFirstName(userRegistration.getFirstName());
	            user.setLastName(userRegistration.getLastName());
	            user.setPhoneNumber(userRegistration.getPhoneNumber());
	            //user.setUserPassword(getEncodedPassword(userRegistrationRequest.getUserPassword()));
	            user.setPassword(userRegistration.getPassword());
	            user.setRole("chef");
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
		return ResponseEntity.ok("Dleted successfully");
		
	}
	
	

	
}


