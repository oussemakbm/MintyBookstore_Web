package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.services.UserServiceImpl;

@Service
public class UserUtilities {
	
	@Autowired
	UserServiceImpl userService;
	
		
	public long getCurrentUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = principal instanceof UserDetails ? ((UserDetails)principal).getUsername() : principal.toString();
		
		User user = userService.getUserByUsername(username);
		
		return user.getId();
	}
	
	
	public User getCurrentAuthenticatedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = principal instanceof UserDetails ? ((UserDetails)principal).getUsername() : principal.toString();
		
		return userService.getUserByUsername(username);
	}
	
}
