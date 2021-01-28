package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.services.BookServiceImpl;
import com.project.services.SerieServiceImpl;

@RestController
public class SerieController {
	
	@Autowired
	SerieServiceImpl serieService;
	
	// http://localhost:8081/MintyBook/servlet/addBook
	@PostMapping("/addBook")
	public Serie addSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
		return serie;
	}
	
	// http://localhost:8081/MintyBook/servlet/updateBook
	@PutMapping(value="/updateSerie")
	public void updateSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
	}
	
	// http://localhost:8081/MintyBook/servlet/getBooks
	@PostMapping("/getBooks")
	public List<Serie> getSeries(){
		return serieService.getSeries();
	}
	
	// http://localhost:8081/MintyBook/servlet/deleteBook
	@DeleteMapping("/deleteSerie/{idserie}")
	public void deleteSerie(@PathVariable("idserie") long serieid){
		serieService.deleteSerie(serieid);
	}

}
