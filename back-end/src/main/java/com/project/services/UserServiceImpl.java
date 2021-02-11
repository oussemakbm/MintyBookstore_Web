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

import com.project.DTOs.NewUserDTO;
import com.project.DTOs.PasswordUpdateDTO;
import com.project.DTOs.SignUpRequestDTO;
import com.project.DTOs.UserDTO;
import com.project.converter.UserConverter;
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
	@Autowired
	UserConverter userConverter;
	
	String ROLE_PREFIX = "ROLE_";

	/** Favorites Series **/
	public boolean addToFavoriteSerie(long user_id,long serie_id) {
		if(!(userRepo.existsById(user_id) && serieRepo.existsById(serie_id)))
			return false;
		Serie serie = serieRepo.findById(serie_id).get();
		User user = userRepo.findById(user_id).get();
		if(user.getFavoriteSeries().contains(serie))
			return false;
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
	
	
	/* Users Management by Admin */
	
	

	@Override
	public List<UserDTO> findAllUsers() {
		return userConverter.entitiesToDTOs((List<User>) userRepo.findAll());
	}
	
	
	@Override
	public List<UserDTO> getUsers(String search){
		List<User> list=userRepo.getUsers("%"+search+"%");
		return userConverter.entitiesToDTOs(list);
	}
	
	@Override
	public List<UserDTO> getUsersOrdered(int order, boolean asc){
		List<User> list;
		switch(order) {
		case 1 : list = asc ? userRepo.getUsersByUsernameAsc() : userRepo.getUsersByUsernameDesc();break;
		case 2 : list = asc ? userRepo.getUsersByEmailAsc() : userRepo.getUsersByEmailDesc();break;
		case 3 : list = asc ? userRepo.getUsersByNameAsc() : userRepo.getUsersByNameDesc();break;
		case 4 : list = asc ? userRepo.getUsersByRoleAsc() : userRepo.getUsersByRoleDesc();break;
		default:  list = (List<User>) userRepo.findAll();
		}
		return userConverter.entitiesToDTOs(list);
	}

	@Override
	public boolean deleteUser(long userId) {
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public UserDTO updateUser(UserDTO user) {
		User u= userRepo.findById(user.getId()).get();
		
		if (!user.getAdresse().equalsIgnoreCase(u.getAdresse()))
			u.setAdresse(user.getAdresse());
		if (!user.getEmail().equalsIgnoreCase(u.getEmail()) && !user.getEmail().equals(""))
			u.setEmail(user.getEmail());
		if (!user.getName().equalsIgnoreCase(u.getName()) && !user.getName().equals(""))
			u.setName(user.getName());
		if (!user.getUsername().equals(u.getUsername()) && !user.getUsername().equals(""))
			u.setUsername(user.getUsername());
		if (user.getPicUrl().equals("")) {
			u.setPicUrl("userPics/defaultUser.png");
		}else if (!user.getPicUrl().equals(u.getPicUrl())) {
			u.setPicUrl(user.getPicUrl());
		}
		
		if (!user.getNumTel().equals(u.getNumTel()))
			u.setNumTel(user.getNumTel());
		if (!user.getRole().equalsIgnoreCase(u.getRole()))
			u.setRole(user.getRole());
		
		
		return userConverter.entityToDTO(userRepo.save(u));
	}
	
	@Override
	public UserDTO createUser(NewUserDTO user) {
		User newUser=new User();
		
		newUser.setAdresse(user.getAdresse());
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setRole(user.getRole());
		newUser.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
		newUser.setNumTel(user.getNumTel());
		newUser.setPicUrl(user.getPicUrl());
		
		return userConverter.entityToDTO(userRepo.save(newUser));
	}
	
	@Override
	public String updatePassword(PasswordUpdateDTO pass) {
		if (!userRepo.existsById(pass.getIdUser()))
			return "User does not exist !";
		User user=userRepo.findById(pass.getIdUser()).get();
		int strength=calculatePasswordStrength(pass.getNewPassword(), user);
		if (strength==-1) {
			return "You can't use the old password!";
		}else if(strength == -2) {
			return "The password cannot be one of the given informations";
		}else if (strength == 0) {
			return "The password must be > 8 characters !";
		}else if (strength < 8){
			return "The given password is too weak !";
		}else {
			user.setPassword(new BCryptPasswordEncoder(10).encode(pass.getNewPassword()));
			userRepo.save(user);
		}
		return "SUCCESS";
	}
	
	
public int calculatePasswordStrength(String password, User u){
        
        int iPasswordScore = 0;
        
        if( password.length() < 8 )
            return 0;
        
        if (new BCryptPasswordEncoder(10).matches(password,u.getPassword())) {
        	return -1;
        }
        if (password.equals(u.getName()) || password.equals(u.getNumTel()) || password.equals(u.getUsername()) || password.equals(u.getEmail()))
            return -2;
        
        else if( password.length() >= 10 )
            iPasswordScore += 2;
        else 
            iPasswordScore += 1;
        
       
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;
        
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;
        
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;    
        
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;
    	System.out.println(iPasswordScore);

        return iPasswordScore;
        
    }


}
