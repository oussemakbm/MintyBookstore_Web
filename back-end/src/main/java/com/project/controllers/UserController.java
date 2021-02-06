package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.entities.User;
import com.project.security.UserUtilities;
//import com.project.security.UserUtilities;
import com.project.services.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	UserUtilities userUtil;
	
	
	@GetMapping("/admin/hello")
	@PreAuthorize("hasRole('ADMIN')")
	public String welcome() {
		return "Welcome Admin";
	}
	
	@GetMapping("/client/hello")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public String welcomeClient() {
		return "Welcome Client";
	}
	
	
	@PostMapping("/auth/signup")
	public User signUp(@RequestBody SignUpRequestDTO signUpDto) throws Exception {
		return userService.saveUser(signUpDto);
	}
	
	
	@GetMapping("/profile")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	ResponseEntity<User> getCurrentUserProfile() {
			
			return new ResponseEntity<User>(userUtil.getCurrentAuthenticatedUser(),HttpStatus.ACCEPTED);
	}
	
	
//	@PutMapping("/profile")
//	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')") 
//	ResponseEntity<User> updateProfileInfo(@RequestParam("pic") MultipartFile multipartFile) {
//		
//	}
	
	
	
}
