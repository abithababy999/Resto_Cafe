package com.resto.orderservice.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/api/order", "/api/order/{id}/all").hasRole("USER")
	            .antMatchers("api/order/{id}/cartitems").hasAnyRole("USER","CHEF")
	            .antMatchers("/api/order/all", "/api/inventory/all", "/api/order/chef/{id}").hasRole("CHEF")
	            .anyRequest().authenticated()
	            .and()
	        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    // Other security configurations
	}

}