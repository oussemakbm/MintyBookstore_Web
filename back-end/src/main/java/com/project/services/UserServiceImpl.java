package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.CommandList;
import com.project.entities.User;
import com.project.repos.UserRepo;


@Service
public class UserServiceImpl implements UserDetailsService { 
	
	@Autowired
	UserRepo userRepo;
	
	String ROLE_PREFIX = "ROLE_";

	/* Favorites Series */
//	@Override
//	public void addToFavoriteSerie(Long user_id,Long serie_id) {
//		userRepo.addToFavoriteSerie(user_id,serie_id);
//	}
//
//	@Override
//	public void deleteFromFavoriteSerie(Long user_id,Long serie_id) {
//		//userRepo.deleteFromFavoriteSerie(user_id,serie_id);		
//	}
//
//	@Override
//	public List<Serie> getAllFavoriteSeries(Long user_id) {
//		return userRepo.getAllFavoriteSeries(user_id);
//	}
//
//	@Override
//	public Serie getFavoriteSerie(Long user_id,Long serie_id) {
//		return	getFavoriteSerie(user_id, serie_id);
//	}
	
	
	/*	Authentication and Signup Logic	*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		
//		 Les Roles dima 3and'hom prefix "ROLE_" (Spring security restrictions)
//		user.getRole() = ROLE_CLIENT
		authoritiesList.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authoritiesList);
	}
	
	public User saveUser(SignUpRequestDTO user) {
		user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
//		UserRole = ROLE_CLIENT
		User userToSave = new User(user.getName(),user.getUsername(), user.getEmail(), user.getPassword(), ROLE_PREFIX + "CLIENT"); 
		return userRepo.save(userToSave);
	}
	
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
		

}
