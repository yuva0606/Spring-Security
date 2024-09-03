package com.raiden.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.raiden.app.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class ConfigFile {
	
	@Autowired
	private UserDetailsServiceImp userDetailsService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		//Creating Dao Auth provider to verify users using db
		DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
		
		//Setting up UserDetailsService object
		provider.setUserDetailsService(userDetailsService);
		//Setting up Password Encoder
		provider.setPasswordEncoder(new BCryptPasswordEncoder(8));
		
		return provider;
	}
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//disabling csrf
		http.csrf(csrf -> csrf.disable())
			//securing all the requests
			.authorizeHttpRequests(request -> request.anyRequest().authenticated())
			// to enabling form login, default form login
//			.formLogin(Customizer.withDefaults())
			//enabling basic authentication of spring security
			.httpBasic(Customizer.withDefaults())
			//making the session as Stateless
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
}
