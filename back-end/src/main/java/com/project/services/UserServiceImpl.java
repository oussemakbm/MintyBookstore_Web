package com.project.services;

import java.util.ArrayList;
import java.util.List;
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
	public void addToFavoriteSerie(long user_id,long serie_id) {
		Serie serie = serieRepo.findById(serie_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().add(serie);
	}

	public void deleteFromFavoriteSerie(long user_id,long serie_id) {
		Serie serie = serieRepo.findById(serie_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().remove(serie);	
	}

	public List<Serie> getAllFavoriteSeries(long user_id) {
		User user = userRepo.findById(user_id).get();
		return user.getFavoriteSeries();
	}

	public void cleanFavoriteSeries(long user_id) {
		User user = userRepo.findById(user_id).get();
		user.getFavoriteSeries().clear();
	}
	
	public List<Serie> findFavoriteSerieByName(long user_id, String name){
		User user = userRepo.findById(user_id).get();
		List <Serie> series = user.getFavoriteSeries().stream()
				.filter(s -> s.getName().toUpperCase().contains(name)).collect(Collectors.toList());
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
