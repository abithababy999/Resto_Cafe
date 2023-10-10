package com.resto.authservice.dto;

import com.resto.authservice.model.User;

public class JwtResponse {


    private String jwtToken;
    
    public JwtResponse() {
    	
    }



	public JwtResponse(String jwtToken) {
	
		this.jwtToken = jwtToken;
	}




	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}





}
