package com.raiden.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.raiden.app.model.Users;
import com.raiden.app.repo.UserRepo;

@Service
public class UsersService { //Class to manage Users 

	@Autowired
	private UserRepo userRepo;
	
	//creating Bcrypt password encoder with strength 8
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
	
	//adding new Users to database with encrypted passwords
	public Users addUser(Users user) {
		//updating the plaintext password to encrypted password
		user.setPassword(encoder.encode(user.getPassword()));
		//saving to data using JPA
		return userRepo.save(user);
	}

	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();	}
}
