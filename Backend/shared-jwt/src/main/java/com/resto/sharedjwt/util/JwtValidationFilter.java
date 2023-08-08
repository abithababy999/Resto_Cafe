package com.resto.sharedjwt.util;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	  @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String authorizationHeader = request.getHeader("Authorization");

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            try {
	                Claims claims = jwtUtil.getAllClaimsFromToken(token);
	                SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(claims));
	            } catch (JwtException e) {
	          
	            }
	        }

	        filterChain.doFilter(request, response);
	    }
}
