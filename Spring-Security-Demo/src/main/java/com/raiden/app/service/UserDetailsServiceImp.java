package com.raiden.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raiden.app.model.Users;
import com.raiden.app.repo.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Getting User from database based on the username entered by user to login
		Users user = userRepo.findById(username).get();
		
		//if user with that given username not found
		if(user == null)
			throw new UsernameNotFoundException("not found");
		//if user with that username exists then creating UserDetails
		return new UserDetailsImp(user);
	}

}
