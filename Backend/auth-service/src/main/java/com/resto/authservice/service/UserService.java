package com.resto.authservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resto.authservice.dto.UserRegistrationRequest;
import com.resto.authservice.dto.UserResponse;
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
	            System.out.println(userRegistration.getUserPassword());
	            user.setPassword(getEncodedPassword(userRegistration.getUserPassword()));
//	            user.setPassword(userRegistration.getPassword());
	            user.setRole("ROLE_USER");
	          return ResponseEntity.ok(userRepository.save(user));
	        }
	    }
	
	public ResponseEntity<User> userAuthorRegister(UserRegistrationRequest userRegistration,String role)
	{
	
	
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
	           user.setPassword(getEncodedPassword(userRegistration.getUserPassword()));
	           user.setRole("ROLE_CHEF");
	           if(role.equals("ADMIN")) {
	        	   user.setRole("ROLE_ADMIN"); 
	           }
	          
	          return ResponseEntity.ok(userRepository.save(user));
	        }
	    }
	public ResponseEntity<String> deleteChef(String userName)
	{
		Optional<User> chef=userRepository.findByUserNameAndRole(userName,"ROLE_CHEF");
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
	
	
	
	public ResponseEntity<User> findChef(Long id){
		Optional<User> temp=userRepository.findById(id);
		if(temp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(temp.get());
		
		
	}
	
	
	public ResponseEntity<UserResponse> findByUserName(String userName){
		Optional<User> temp=userRepository.findByUserName(userName);
		if(temp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user=temp.get();
		UserResponse userResponse=new UserResponse();
		userResponse.setUserName(user.getUserName());
		userResponse.setRole(user.getRole());
		userResponse.setPassword(user.getPassword());
	
		return ResponseEntity.ok(userResponse);
	}
	
	public ResponseEntity<List<User>> getAllAdminAndChef(){
		return ResponseEntity.ok(userRepository.findAllAdminsAndChefs());
	}
	
	
	
	
	   private String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }

	
}


