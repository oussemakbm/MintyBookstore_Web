package com.project.controllers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Langue;
import com.project.services.LanguageService;
@RestController
public class LangueController {
	
	@Autowired
	LanguageService langueService;
	
	@PostMapping("/addLangue")
	public Langue addBook(@RequestBody Langue langue) {
		langueService.addOrUpdateLangue(langue);
		return langue;
	}
	@PutMapping(value="/updateLangue")
	public void updateBok(@RequestBody Langue langue) {
		langueService.addOrUpdateLangue(langue);
	}
	
	@PostMapping("/getLanguages")
	public List<Langue> listLangues(){
		return langueService.getLangues();
		
	}
	@DeleteMapping("/deleteLangue/{idLangue}")
	public void deleteLangue(@PathVariable("idLangue") long langueId) {
		langueService.deleteById(langueId);
	}
	

}
