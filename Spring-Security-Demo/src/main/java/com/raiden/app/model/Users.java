package com.raiden.app.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {

	@Id
	@Nonnull
	String username;
	
	@Nonnull
	String Password;
	
	public Users() {
		
	}

	public String getUsername() {
		return username;
	}

	public Users(String username, String password) {
		super();
		this.username = username;
		Password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
