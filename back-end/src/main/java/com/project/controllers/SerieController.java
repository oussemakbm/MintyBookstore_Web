package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.project.services.SerieServiceImpl;

@RestController
public class SerieController {
	
	@Autowired
	SerieServiceImpl serieService;

}
