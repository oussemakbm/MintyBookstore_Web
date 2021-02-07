package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.SerieDTO;
import com.project.converter.SerieConverter;
import com.project.entities.Serie;
import com.project.services.UserServiceImpl;

@RestController
@RequestMapping(value="/serie")
public class FavoriteSerieController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	SerieConverter serieConverter;
	
	/***  Favorites Series ***/
	@PostMapping("/addToFavoriteSeries/{iduser}/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> addToFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		if(userService.addToFavoriteSerie(user_id,serie_id))
			return ResponseEntity.status(HttpStatus.OK)
			        .body("Added Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
			        .body("Not Added Yet !");
	}
		
	@GetMapping("/getAllFavoriteSeries/{iduser}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<SerieDTO>> getAllFavoriteSeries(@PathVariable("iduser") int user_id){
		if(userService.getAllFavoriteSeries(user_id) != null){
			List<Serie> series = userService.getAllFavoriteSeries(user_id);
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(series), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@DeleteMapping(value="/deleteFromFavoriteSerie/{iduser}/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> deleteFromFavoriteSerie(@PathVariable("iduser") int user_id, @PathVariable("idserie") int serie_id){
		if(userService.deleteFromFavoriteSerie(user_id,serie_id))
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Deleted Yet !");
	}
		
	@DeleteMapping("/cleanFavoriteSeries/{iduser}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> cleanFavoriteSeries(@PathVariable("iduser") int user_id){
		if(userService.cleanFavoriteSeries(user_id))
			return ResponseEntity.status(HttpStatus.OK).body("Cleaned Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Cleaned Yet !");
	}
	
	@GetMapping("/findFavoriteSerieByName/{iduser}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<SerieDTO>> findFavoriteSerieByName(@PathVariable("iduser") int user_id, @RequestParam("name") String name){
		if(userService.findFavoriteSerieByName(user_id,name) != null){
			List<Serie> series = userService.findFavoriteSerieByName(user_id,name);
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(series), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
