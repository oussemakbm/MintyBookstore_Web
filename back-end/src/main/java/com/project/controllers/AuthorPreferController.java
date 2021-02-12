package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.DTOs.AuthorDTO;
import com.project.DTOs.SerieDTO;
import com.project.converter.AuthorConverter;
import com.project.converter.SerieConverter;
import com.project.entities.Author;
import com.project.entities.Serie;
import com.project.security.UserUtilities;
import com.project.services.AuthorPreferService;
import com.project.services.AuthorPreferServiceImpl;

@RestController
@RequestMapping(value="/author")
public class AuthorPreferController {
@Autowired 
AuthorPreferServiceImpl authorService ; 
@Autowired
AuthorConverter authorConverter;
@Autowired
UserUtilities userUtilities ;
@PostMapping("/addAuthorPrefer/{idauthor}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<String> addAuthorPrefer( @PathVariable("idauthor") long author_id){
	long user_id=userUtilities.getCurrentUserId();
	if(authorService.addAuthorPrefer(user_id , author_id))
		return ResponseEntity.status(HttpStatus.OK)
		        .body("l'auteur est ajoute ");
	else
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
		        .body("pas encore  ");
}
@GetMapping("/getAllFavoriteAuthors")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")

public ResponseEntity<List<AuthorDTO>> getAllFavoriteAuthors(){
	long user_id=userUtilities.getCurrentUserId();
	if(authorService.getAllPreferAuthor(user_id) != null){
		List<Author> authors = authorService.getAllPreferAuthor(user_id);
		return new ResponseEntity<List<AuthorDTO>>(authorConverter.entitiesToDTOs(authors), HttpStatus.OK);
	}else
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
}
@DeleteMapping(value="/deleteFromAuthor/{idauthor}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<String> deleteAuthorPrefer(@PathVariable("idauthor") int author_id){
	long user_id=userUtilities.getCurrentUserId();
	if(authorService.deleteAuthorPrefer(user_id,author_id))
		return ResponseEntity.status(HttpStatus.OK).body("l'auteur est supprime !");
	else
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("pas encore !");
}
@GetMapping("/findPreferAuthorByName")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<List<AuthorDTO>> findPreferAuthorByName( @RequestParam ("name") String name){
	long user_id=userUtilities.getCurrentUserId();
	if(authorService.findPreferAuthorByName(user_id,name) != null){
		List<Author> authors = authorService.findPreferAuthorByName(user_id,name);
		return new ResponseEntity<List<AuthorDTO>>(authorConverter.entitiesToDTOs(authors), HttpStatus.OK);
	}else
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
}

}
	