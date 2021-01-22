package com.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.project.services.BookServiceImpl;

@RestController
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
}
