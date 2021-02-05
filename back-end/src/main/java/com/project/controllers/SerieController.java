package com.project.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.SerieDTO;
import com.project.converter.SerieConverter;
import com.project.entities.Serie;
import com.project.services.SerieServiceImpl;

@RestController
@RequestMapping(value="/serie")
public class SerieController {
	
	@Autowired
	SerieServiceImpl serieService;
	@Autowired
	SerieConverter serieConverter;
	
	@PostMapping("/addSerie")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Serie> addSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
		if(Objects.isNull(serie.getId()))
			return new ResponseEntity<Serie>(serie, HttpStatus.NOT_ACCEPTABLE);
		return  new ResponseEntity<Serie>(serie, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateSerie")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> updateSerie(@RequestBody Serie serie){
		serieService.addOrUpdateSerie(serie);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Updated Successfully !");
	}
	
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	@GetMapping("/getSeries")
	public ResponseEntity<List<Serie>> getSeries(){
		return  new ResponseEntity<List<Serie>>(serieService.getSeries(),HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	@GetMapping("/getSerie/{idserie}")
	public ResponseEntity<SerieDTO> getSerie(@PathVariable("idserie") long serieid){
		Serie serie = serieService.findSerieById(serieid);
		return  new ResponseEntity<SerieDTO>(serieConverter.entityToDTO(serie),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSerie/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteSerie(@PathVariable("idserie") long serieid){
		serieService.deleteSerie(serieid);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");
	}

}
