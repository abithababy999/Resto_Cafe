package com.resto.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.resto.authservice.dto.JwtRequest;
import com.resto.authservice.dto.JwtResponse;
import com.resto.authservice.model.User;
import com.resto.authservice.repository.UserRepository;
import com.resto.authservice.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> temp = userRepository.findByUserName(username);

		if (temp.isPresent()) {
			User user = temp.get();
			
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					user.getAuthorities());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		User user = userRepository.findByUserName(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
