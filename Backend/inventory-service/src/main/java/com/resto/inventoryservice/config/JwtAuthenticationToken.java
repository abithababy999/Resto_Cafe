package com.resto.inventoryservice.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationToken implements Authentication {

    private final Claims claims;
    public JwtAuthenticationToken(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Extract roles/permissions from claims and return as authorities
        List<?> roles = this.claims.get("roles", List.class); // Assuming roles are stored under the "roles" claim in the JWT

        if (roles == null) {
            return Collections.emptyList();
        }

        // Convert roles to a collection of SimpleGrantedAuthority objects
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    	
       
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


    // Other methods from Authentication interface
}
