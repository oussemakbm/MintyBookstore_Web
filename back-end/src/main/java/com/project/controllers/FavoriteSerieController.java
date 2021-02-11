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
import com.project.security.UserUtilities;
import com.project.services.UserServiceImpl;

@RestController
@RequestMapping(value="/serie")
public class FavoriteSerieController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	SerieConverter serieConverter;
	
	/***  Favorites Series ***/
	@PostMapping("/addToFavoriteSeries/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> addToFavoriteSerie(@PathVariable("idserie") int serie_id){
		if(userService.addToFavoriteSerie(serie_id))
			return ResponseEntity.status(HttpStatus.OK)
			        .body("Added Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
			        .body("Not Added Yet !");
	}
		
	@GetMapping("/getAllFavoriteSeries")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<SerieDTO>> getAllFavoriteSeries(){
		if(userService.getAllFavoriteSeries() != null){
			List<Serie> series = userService.getAllFavoriteSeries();
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(series), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@DeleteMapping(value="/deleteFromFavoriteSerie/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> deleteFromFavoriteSerie(@PathVariable("idserie") int serie_id){
		if(userService.deleteFromFavoriteSerie(serie_id))
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Deleted Yet !");
	}
		
	@DeleteMapping("/cleanFavoriteSeries")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> cleanFavoriteSeries(){
		if(userService.cleanFavoriteSeries())
			return ResponseEntity.status(HttpStatus.OK).body("Cleaned Successfully !");
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Cleaned Yet !");
	}
	
	@GetMapping("/findFavoriteSerieByName")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<SerieDTO>> findFavoriteSerieByName(@RequestParam("name") String name){
		if(userService.findFavoriteSerieByName(name) != null){
			List<Serie> series = userService.findFavoriteSerieByName(name);
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(series), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
