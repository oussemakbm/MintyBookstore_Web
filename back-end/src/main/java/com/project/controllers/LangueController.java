package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.entities.Langue;
import com.project.services.LanguageService;

public class LangueController {
	
	@Autowired
	LanguageService langueService;
	
	// http://localhost:8081/MintyBook/servlet/addBook
	@PostMapping("/addLangue")
	public Langue addBook(@RequestBody Langue langue) {
		langueService.addOrUpdateLangue(langue);
		return langue;
	}
	
	

}
