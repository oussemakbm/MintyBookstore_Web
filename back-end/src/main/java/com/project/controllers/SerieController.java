package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.services.BookServiceImpl;
import com.project.services.SerieServiceImpl;

@RestController
@RequestMapping(value="/serie")
public class SerieController {
	
	@Autowired
	SerieServiceImpl serieService;
	
	// http://localhost:8082/MintyBook/servlet/addSerie
	@PostMapping("/addSerie")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Serie addSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
		return serie;
	}
	
	// http://localhost:8082/MintyBook/servlet/updateSerie
	@PutMapping(value="/updateSerie")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void updateSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
	}
	
	// http://localhost:8082/MintyBook/servlet/getSeries
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	@PostMapping("/getSeries")
	public List<Serie> getSeries(){
		return serieService.getSeries();
	}
	
	// http://localhost:8082/MintyBook/servlet/deleteSerie/5
	@DeleteMapping("/deleteSerie/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void deleteSerie(@PathVariable("idserie") long serieid){
		serieService.deleteSerie(serieid);
	}

}
