package com.project.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
}
