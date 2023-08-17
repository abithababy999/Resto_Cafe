package com.resto.orderservice.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resto.orderservice.dto.UserDto;
import com.resto.orderservice.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;



	@Override
	public UserDetails loadUserByUsername(String jwtToken) throws UsernameNotFoundException {
		String userName=jwtUtil.getUsernameFromToken(jwtToken);
		if(userName==null)
			throw new UsernameNotFoundException("No username found in token");
		
		UserDto temp = fetchUser(userName,jwtToken);

		if (temp != null) {
			UserDto user = temp;

			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					user.getAuthorities());
		} else {
	System.out.println("Error "+temp);
	return temp;
			
		}
		
	}



	private UserDto fetchUser(String userName,String jwtToken) {
		try {
			String url="http://localhost:8088/api/auth/user/" + userName;
			HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Bearer " + jwtToken);

	        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

	        ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserDto.class);
//			UserDto response =  restTemplate.getForObject(url, UserDto.class);
			return response.getBody();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null ;
		}
	}
}
