package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.DTOs.SignUpRequestDTO;
import com.project.entities.Author;
import com.project.entities.Serie;
import com.project.entities.User;
import com.project.repos.UserRepo;


@Service
public class UserServiceImpl implements UserDetailsService { 
	
	@Autowired
	UserRepo userRepo;
	

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
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
	
	public User saveUser(SignUpRequestDTO user) {
		user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
		User userToSave = new User(user.getName(),user.getUsername(), user.getEmail(), user.getPassword(), "CLIENT");
		return userRepo.save(userToSave);
	}
	

}
