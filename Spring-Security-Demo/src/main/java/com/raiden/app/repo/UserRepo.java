package com.raiden.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raiden.app.model.Users;

public interface UserRepo extends JpaRepository<Users, String> {

}
