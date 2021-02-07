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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<SerieDTO> addSerie(@RequestBody SerieDTO seriedto){
		Serie serie = serieConverter.DTOToentity(seriedto);
		serie = serieService.addOrUpdateSerie(serie);
		if(Objects.isNull(serie.getId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return  new ResponseEntity<SerieDTO>(serieConverter.entityToDTO(serie), HttpStatus.OK);
	}
	
	@PutMapping(value="/updateSerie")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> updateSerie(@RequestBody SerieDTO seriedto){
		Serie serie = serieConverter.DTOToentity(seriedto);
		serieService.addOrUpdateSerie(serie);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Updated Successfully !");
	}
	
	@GetMapping("/getSeries")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<SerieDTO>> getSeries(){
		if(serieService.getSeries() != null){
			List<Serie> series = serieService.getSeries();
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(series), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getSerie/{idserie}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<SerieDTO> getSerie(@PathVariable("idserie") long serieid){
		Serie serie = serieService.findSerieById(serieid);
		if(serie != null)
			return  new ResponseEntity<SerieDTO>(serieConverter.entityToDTO(serie),HttpStatus.OK);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/findSerieByName")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<SerieDTO>> findFavoriteSerieByName(@RequestParam("name") String name){
		if(serieService.findSerieByName(name) != null){
			return new ResponseEntity<List<SerieDTO>>(serieConverter.entitiesToDTOs(serieService.findSerieByName(name)), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@DeleteMapping("/deleteSerie/{idserie}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteSerie(@PathVariable("idserie") long serieid){
		serieService.deleteSerie(serieid);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");
	}

}
