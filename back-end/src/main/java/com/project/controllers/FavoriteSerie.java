package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	// http://localhost:8081/MintyBook/servlet/addToFavoriteSeries/1/5
	@PostMapping("/addToFavoriteSeries/{iduser}/{idserie}")
	public void addToFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.addToFavoriteSerie(user_id,serie_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/deleteFromFavoriteSerie/1/5
	@PutMapping(value="/deleteFromFavoriteSerie")
	public void deleteFromFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		userService.deleteFromFavoriteSerie(user_id,serie_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/getAllFavoriteSeries/1
	@PostMapping("/getAllFavoriteSeries/{iduser}")
	public List<Serie> getAllFavoriteSeries(@PathVariable("iduser") int user_id){
		return userService.getAllFavoriteSeries(user_id);
	}
		
	// http://localhost:8081/MintyBook/servlet/cleanFavoriteSeries/1
	@DeleteMapping("/cleanFavoriteSeries/{iduser}")
	public void cleanFavoriteSeries(@PathVariable("iduser") int user_id){
		userService.cleanFavoriteSeries(user_id);
	}
	
	// http://localhost:8081/MintyBook/servlet/getAllFavoriteSeries/1/hunger
	@PostMapping("/getAllFavoriteSeries")
	public List<Serie> findFavoriteSerieByName(@PathVariable("iduser") int user_id, @PathVariable("name") String name){
		return userService.findFavoriteSerieByName(user_id,name);
	}
}
