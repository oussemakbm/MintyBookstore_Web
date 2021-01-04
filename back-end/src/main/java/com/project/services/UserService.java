package com.project.services;

import java.util.List;

import com.project.entities.Serie; 

public interface UserService {
	
	
	/* Favorites Series */
	public void addToFavoriteSerie(Long user_id,Long serie_id);
	
	public void deleteFromFavoriteSerie(Long user_id,Long serie_id);
	
	public List<Serie> getAllFavoriteSeries(Long user_id);

	public Serie getFavoriteSerie(Long user_id,Long serie_id);
}
