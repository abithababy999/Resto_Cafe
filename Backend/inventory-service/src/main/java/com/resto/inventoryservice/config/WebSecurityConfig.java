package com.resto.inventoryservice.config;
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
    private JwtValidationFilter jwtValidationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors();
		http.csrf().disable().authorizeRequests()
				.antMatchers("/chef").hasAnyRole("USER", "CHEF").anyRequest().authenticated()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class);
        // Other security configurations
    }
}
