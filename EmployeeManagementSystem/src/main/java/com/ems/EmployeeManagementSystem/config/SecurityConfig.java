package com.ems.EmployeeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("rupesh").password("{noop}12345").roles("ADMIN").build();
		UserDetails user2 = User.withUsername("deepak").password("{noop}12345").build();

		return new InMemoryUserDetailsManager(user,user2);
	}
	
	
	 
	 	
	 	@Bean
	 	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 	    http
	 	   .csrf(csrf -> csrf.disable())
	 	        .authorizeHttpRequests(auth -> auth.requestMatchers("/emp/**").permitAll()
	 	            .anyRequest().authenticated()
	 	        )
	 	        .httpBasic();

	 	    return http.build();
	 	}


}
