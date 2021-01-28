package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.entities.Langue;
import com.project.services.LanguageService;

public class LangueController {
	
	@Autowired
	LanguageService langueService;
	
	// http://localhost:8081/MintyBook/servlet/addLangue
	@PostMapping("/addLangue")
	public Langue addBook(@RequestBody Langue langue) {
		langueService.addOrUpdateLangue(langue);
		return langue;
	}
	// http://localhost:8081/MintyBook/servlet/updateLangue
	@PutMapping(value="/updateLangue")
	public void updateBok(@RequestBody Langue langue) {
		langueService.addOrUpdateLangue(langue);
	}
	
	// http://localhost:8081/tp-timesheet/servlet/getLanguages
	@PostMapping("/getLanguages")
	public List<Langue> listLangues(){
		return langueService.getLangues();
		
	}
	

}
