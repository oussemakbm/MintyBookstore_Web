package com.project.controllers;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.entities.Author;
import com.project.entities.Serie;
import com.project.services.AuthorService;
import com.project.services.AuthorServiceImpl;



@RestController
public class AuthorController {
@Autowired 
AuthorServiceImpl authorService ;
//http://localhost:8082/MintyBook/servlet/addAuthor
	@PostMapping("/addAuthor")
	public Author addAuthor(@RequestBody Author author){
		authorService.addAuthor(author);
	return author ;
	}
	
	
	// http://localhost:8082/MintyBook/servlet/getAuthors
	@PostMapping("/getAuthors")
	public List<Author> getAuthors(){
		return authorService.getAuthors();
	}
	// http://localhost:8082/MintyBook/servlet/deleteById
		@DeleteMapping("/deleteAuthor/{idauthor}")
		public void deleteById(@PathVariable("idauthor") long idauthor) {
			authorService.deleteById(idauthor);
		}
		// http://localhost:8082/MintyBook/servlet/findAuthorById
			@PostMapping("/findAuthorByid/{idauthor}")
			public void findAuthorById(@PathVariable("idauthor") long idauthor) {
			authorService.findAuthorById(idauthor);
				}
	// http://localhost:8082/MintyBook/servlet/deleteAuthor
	@DeleteMapping("/deleteSerie")
	public void deleteAuthor(@RequestBody Author author){
		authorService.deleteAuthor(author);
	}
}
