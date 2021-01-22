package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.project.entities.Serie;
import com.project.repos.UserRepo;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	

	/* Favorites Series */
	/*
	@Override
	public void addToFavoriteSerie(Long user_id,Long serie_id) {
		userRepo.addToFavoriteSerie(user_id,serie_id);
	}*/

	/*@Override
	public void deleteFromFavoriteSerie(Long user_id,Long serie_id) {
		//userRepo.deleteFromFavoriteSerie(user_id,serie_id);		
	}

	@Override
	public List<Serie> getAllFavoriteSeries(Long user_id) {
		return userRepo.getAllFavoriteSeries(user_id);
	}

	@Override
	public Serie getFavoriteSerie(Long user_id,Long serie_id) {
		return	getFavoriteSerie(user_id, serie_id);
	}*/

}
