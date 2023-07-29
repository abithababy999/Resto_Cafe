package com.resto.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.resto.authservice.model.User;
import com.resto.authservice.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> temp = user.findByUserName(username);
		if(temp.isPresent())
		{
			return temp.get();
		}
		return null;
	}
	
	public User create(User myUser) {
		return user.save(myUser);
	}
}
	
