package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.DTOs.UserDTO;
import com.project.entities.Serie; 
import com.project.entities.User;
import com.project.repos.UserRepo;

public interface UserService {
	
	
	/* Favorites Series */
	public boolean addToFavoriteSerie(long serie_id);
	
	public boolean deleteFromFavoriteSerie(long serie_id);
	
	public List<Serie> getAllFavoriteSeries();

	public boolean cleanFavoriteSeries();
	
	public List<Serie> findFavoriteSerieByName(String serie_id);
	
	
	
	/* User Management by Admin */
	
	public List<UserDTO> findAllUsers();
	
	public List<UserDTO> getUsersOrdered(int order, boolean asc);
	
	public List<UserDTO> getUsers(String search);
	
	public void deleteUser(long userId);
	
	public UserDTO updateUser(UserDTO user);
	
}
