package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Serie;
import com.project.services.UserServiceImpl;

@RestController
public class FavoriteSerie {

	@Autowired
	UserServiceImpl userService;
	
	/***  Favorites Series ***/
	@PostMapping("/addToFavoriteSeries/{iduser}/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public void addToFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.addToFavoriteSerie(user_id,serie_id);
	}
		
	@PutMapping(value="/deleteFromFavoriteSerie/{iduser}/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public void deleteFromFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.deleteFromFavoriteSerie(user_id,serie_id);
	}
		
	@PostMapping("/getAllFavoriteSeries/{iduser}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public List<Serie> getAllFavoriteSeries(@PathVariable("iduser") int user_id){
		return userService.getAllFavoriteSeries(user_id);
	}
		
	@DeleteMapping("/cleanFavoriteSeries/{iduser}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public void cleanFavoriteSeries(@PathVariable("iduser") int user_id){
		userService.cleanFavoriteSeries(user_id);
	}
	
	@PostMapping("/getAllFavoriteSeries")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public List<Serie> findFavoriteSerieByName(@PathVariable("iduser") int user_id, @PathVariable("name") String name){
		return userService.findFavoriteSerieByName(user_id,name);
	}
}
