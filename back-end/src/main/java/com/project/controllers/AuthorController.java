package com.project.controllers;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.entities.Author;
import com.project.services.AuthorService;



@RestController
public class AuthorController {
@Autowired 
AuthorService authorService ;

@RequestMapping(value = "/authors/", method = RequestMethod.POST)
public ResponseEntity<?> createAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
	

	authorService.addAuthor(author);

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/api/authors/{id}").buildAndExpand(author.getId()).toUri());
	return new ResponseEntity<String>(headers, HttpStatus.CREATED);}

	@RequestMapping(value = "/authors/", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> listAllAuthors() {
		
		List<Author> authors = authorService.getAuthors();
		
		if (authors.isEmpty()) {
			return new ResponseEntity<List<Author>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id){
		Author author = authorService.findAuthorById(id);
		authorService.deleteAuthor(author);
		return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
	}
}
