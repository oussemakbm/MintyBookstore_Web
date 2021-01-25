package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	
	@GetMapping("/api/profile")
	ResponseEntity<User> getCurrentUserProfile() {
		
		boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		
		if (isAuthenticated) {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username = principal instanceof UserDetails ? ((UserDetails)principal).getUsername() : principal.toString();
			
			User currentAuthenticatedUser = userService.getUserByUsername(username);
			
			return new ResponseEntity<User>(currentAuthenticatedUser,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}
	
}
