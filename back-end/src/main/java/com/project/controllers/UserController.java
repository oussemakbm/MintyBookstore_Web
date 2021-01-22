package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.AuthRequestDTO;
import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.User;
import com.project.services.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/hello")
	public String welcome() {
		return "Welcome";
	}
	
//	@PostMapping("/api/auth/login")
//	public ResponseEntity<User> login(@RequestBody AuthRequestDTO authRequest ) throws Exception {
//		
//		return ResponseEntity.ok()
//		
//	}
	
	@PostMapping("/api/auth/signup")
	public User signUp(@RequestBody SignUpRequestDTO signUpDto) throws Exception {
		return userService.saveUser(signUpDto);
	}
	
}
