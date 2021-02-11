package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.DTOs.NewUserDTO;
import com.project.DTOs.UserDTO;
import com.project.entities.Serie; 
import com.project.entities.User;
import com.project.repos.UserRepo;

public interface UserService {
	
	
	/* Favorites Series */
	public boolean addToFavoriteSerie(long user_id,long serie_id);
	
	public boolean deleteFromFavoriteSerie(long user_id,long serie_id);
	
	public List<Serie> getAllFavoriteSeries(long user_id);

	public boolean cleanFavoriteSeries(long user_id);
	
	public List<Serie> findFavoriteSerieByName(long user_id,String serie_id);
	
	
	
	/* User Management by Admin */
	
	public List<UserDTO> findAllUsers();
	
	public List<UserDTO> getUsersOrdered(int order, boolean asc);
	
	public List<UserDTO> getUsers(String search);
	
	public boolean deleteUser(long userId);
	
	public UserDTO updateUser(UserDTO user);
	
	public UserDTO createUser(NewUserDTO user);
	
}
