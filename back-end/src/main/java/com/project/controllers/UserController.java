package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.User;
import com.project.services.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/api/admin/hello")
	@PreAuthorize("hasRole('ADMIN')")
	public String welcome() {
		return "Welcome Admin";
	}
	
	@GetMapping("/api/client/hello")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public String welcomeClient() {
		return "Welcome Client";
	}
	
	
	@PostMapping("/api/auth/signup")
	public User signUp(@RequestBody SignUpRequestDTO signUpDto) throws Exception {
		return userService.saveUser(signUpDto);
	}
	
}
