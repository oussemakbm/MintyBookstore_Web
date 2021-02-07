package com.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
import com.project.entities.Serie;
import com.project.entities.User;
import com.project.repos.SerieRepo;
import com.project.repos.UserRepo;


@Service
public class UserServiceImpl implements UserDetailsService, UserService{ 
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	SerieRepo serieRepo;
	
	String ROLE_PREFIX = "ROLE_";

	/** Favorites Series **/
	public boolean addToFavoriteSerie(long user_id,long serie_id) {
		if(!(userRepo.existsById(user_id) && serieRepo.existsById(serie_id)))
			return false;
		Serie serie = serieRepo.findById(serie_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().add(serie);
		userRepo.save(user);
		return true;
	}

	public boolean deleteFromFavoriteSerie(long user_id,long serie_id) {
		if(!(userRepo.existsById(user_id) && serieRepo.existsById(serie_id)))
			return false;
		Serie serie = serieRepo.findById(serie_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().remove(serie);	
		userRepo.save(user);
		return true;
	}

	public boolean cleanFavoriteSeries(long user_id) {
		if(!(userRepo.existsById(user_id)))
			return false;
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().clear();
		userRepo.save(user);
		return true;
	}
	
	public List<Serie> getAllFavoriteSeries(long user_id) {
		if(!(userRepo.existsById(user_id)))
			return null;
		User user = userRepo.findById(user_id).get();
		return user.getFavoriteSeries();
	}
	
	public List<Serie> findFavoriteSerieByName(long user_id, String name){
		if(!(userRepo.existsById(user_id)))
			return null;
		User user = userRepo.findById(user_id).get();
		List <Serie> series = user.getFavoriteSeries().stream()
				.filter(s -> s.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
		return series;
	}
	
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
