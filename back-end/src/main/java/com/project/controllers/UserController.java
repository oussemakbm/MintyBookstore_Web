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
import com.project.services.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	UserUtilities userUtil;
	
	
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
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	ResponseEntity<User> getCurrentUserProfile() {
			
			return new ResponseEntity<User>(userUtil.getCurrentAuthenticatedUser(),HttpStatus.ACCEPTED);
	}
	
	
//	@PutMapping("api/profile")
//	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')") 
//	ResponseEntity<User> updateProfileInfo(@RequestParam("pic") MultipartFile multipartFile) {
//		
//	}
	
	
	/***  Favorites Series ***/
	// http://localhost:8081/MintyBook/servlet/addToFavoriteSeries/1/5
	@PostMapping("/addToFavoriteSeries/{iduser}/{idserie}")
	public void addToFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.addToFavoriteSerie(user_id,serie_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/deleteFromFavoriteSerie/1/5
	@PutMapping(value="/deleteFromFavoriteSerie")
	public void deleteFromFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.deleteFromFavoriteSerie(user_id,serie_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/getAllFavoriteSeries/1
	@PostMapping("/getAllFavoriteSeries/{iduser}")
	public List<Serie> getAllFavoriteSeries(@PathVariable("iduser") int user_id){
		return userService.getAllFavoriteSeries(user_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/cleanFavoriteSeries/1
	@DeleteMapping("/cleanFavoriteSeries/{iduser}")
	public void cleanFavoriteSeries(@PathVariable("iduser") int user_id){
		userService.cleanFavoriteSeries(user_id);
	}
	
	// http://localhost:8081/MintyBook/servlet/getAllFavoriteSeries/1/hunger
	@PostMapping("/getAllFavoriteSeries")
	public List<Serie> findFavoriteSerieByName(@PathVariable("iduser") int user_id, @PathVariable("name") String name){
		return userService.findFavoriteSerieByName(user_id,name);
	}
	
	
}
