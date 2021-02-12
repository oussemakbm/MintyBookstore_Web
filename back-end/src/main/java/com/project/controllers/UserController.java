package com.project.controllers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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

import com.project.DTOs.GetOrderedUsersDTO;
import com.project.DTOs.NewUserDTO;
import com.project.DTOs.PasswordUpdateDTO;
import com.project.DTOs.SignUpRequestDTO;
import com.project.DTOs.UserDTO;
import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.entities.User;
import com.project.security.UserUtilities;
//import com.project.security.UserUtilities;
import com.project.services.UserServiceImpl;
import com.project.util.FileUploadUtil;

import io.jsonwebtoken.io.IOException;
//import kong.unirest.HttpResponse;
//import kong.unirest.Unirest;

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
		return new ResponseEntity<User>(userUtil.getCurrentAuthenticatedUser(), HttpStatus.ACCEPTED);
	}

	@PutMapping("/profile")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	ResponseEntity<String> updateProfileInfo(@RequestParam("pic") MultipartFile pic) {
		// Saving image
		String fileName = StringUtils.cleanPath(pic.getOriginalFilename());
		String filePath = "userPics/";
		try {
			FileUploadUtil.saveFile(filePath, fileName, pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("uploaded successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/profile/pic/{path}")

	ResponseEntity<?> getProfilePic(@PathVariable("path") String path) {
		byte[] image = new byte[0];
		try {
			image = FileUtils.readFileToByteArray(new File("userPics/"+ path));
		} catch (IOException | java.io.IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);

	}
	
	
	
	/* User Management by Admin */
	
	
	@GetMapping("/admin/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllUsers(){
		List<UserDTO> list=userService.findAllUsers();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/admin/users/search/{search}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUsersBySearch(@PathVariable("search") String search){
		List<UserDTO> list=userService.getUsers(search);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/admin/users/ordered")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUsersOrdered(@RequestBody GetOrderedUsersDTO ordered){
		List<UserDTO> list=userService.getUsersOrdered(ordered.getOrder(), ordered.isAsc());
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping("/admin/user/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO user){
		UserDTO u=userService.updateUser(user);
		return ResponseEntity.ok().body(u);
	}
	
	@PutMapping("/admin/user/changePassword")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateDTO pass){
		String result=userService.updatePassword(pass);
		if (result.equalsIgnoreCase("SUCCESS"))
			return ResponseEntity.ok().body("Password changed succcessfully !");
		return ResponseEntity.badRequest().body(result);
	}
	
	
	@DeleteMapping("/admin/user/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable("id") long userId){
		boolean deleted = userService.deleteUser(userId);
		if (deleted) {
			return ResponseEntity.ok().body("User deleted successfully !");
		}
		return ResponseEntity.badRequest().body("User does not exist !");
	}
	
	@PostMapping("/admin/user/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody NewUserDTO user){
		UserDTO newUser = userService.createUser(user);
		
			return ResponseEntity.ok().body(newUser);
		
	}
	/*
	@GetMapping("/admin/user/testemail/{email}")
	public ResponseEntity<?> checkEmail(@PathVariable("email") String email){
		HttpResponse<String> response = Unirest.get("https://bouncer-email-verification.p.rapidapi.com/v1/email/verify?email=ihebferjani04%40gmail.com&timeout=10")
				.header("x-rapidapi-key", "9528c6cab5msh0005b7fa923d823p167dc5jsnc299aec50460")
				.header("x-rapidapi-host", "bouncer-email-verification.p.rapidapi.com")
				.asString();
		return ResponseEntity.ok().body(response);
	}
	*/
}
