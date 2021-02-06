package com.project.controllers;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.entities.User;
import com.project.security.UserUtilities;
//import com.project.security.UserUtilities;
import com.project.services.UserServiceImpl;
import com.project.util.FileUploadUtil;

import io.jsonwebtoken.io.IOException;


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
	@PutMapping("/profile")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')") 
	ResponseEntity<String> updateProfileInfo(@RequestParam("pic") MultipartFile pic) {
//		Saving image
		String fileName = StringUtils.cleanPath(pic.getOriginalFilename());
		String filePath = "userPics/"+fileName+java.sql.Date.valueOf(LocalDate.now().toString());
		try {
			FileUploadUtil.saveFile( filePath, fileName, pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("uploaded successfully",HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping
	(
		value="/profile/pic/{path}",
		produces= MediaType.APPLICATION_OCTET_STREAM_VALUE
	)
	@ResponseBody
	byte[] getProfilePic(@PathVariable("path") String path) throws IOException {
		InputStream in = getClass()
				.getResourceAsStream(path);
		try {
			return IOUtils.toByteArray(in);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
