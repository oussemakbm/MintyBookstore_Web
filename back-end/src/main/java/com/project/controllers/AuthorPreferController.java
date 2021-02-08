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
import com.project.services.AuthorPreferService;
import com.project.services.AuthorPreferServiceImpl;

@RestController
@RequestMapping(value="/author")
public class AuthorPreferController {
@Autowired 
AuthorPreferServiceImpl authorService ; 
@Autowired
AuthorConverter authorConverter;

@PostMapping("/addAuthorPrefer/{iduser}/{idserie}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<String> addAuthorPrefer(@PathVariable("iduser") long user_id, @PathVariable("idauthor") long author_id){
	if(authorService.addAuthorPrefer(user_id , author_id))
		return ResponseEntity.status(HttpStatus.OK)
		        .body("l'auteur est ajoute ");
	else
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
		        .body("pas encore  ");
}
@GetMapping("/getAllFavoriteAuthors/{iduser}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<List<AuthorDTO>> getAllFavoriteAuthors(@PathVariable("iduser") int user_id){
	if(authorService.getAllPreferAuthor(user_id) != null){
		List<Author> authors = authorService.getAllPreferAuthor(user_id);
		return new ResponseEntity<List<AuthorDTO>>(authorConverter.entitiesToDTOs(authors), HttpStatus.OK);
	}else
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
}
@DeleteMapping(value="/deleteFromAuthor/{iduser}/{idserie}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<String> deleteAuthorPrefer(@PathVariable("iduser") int user_id, @PathVariable("idserie") int author_id){
	if(authorService.deleteAuthorPrefer(user_id,author_id))
		return ResponseEntity.status(HttpStatus.OK).body("l'auteur est supprime !");
	else
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("pas encore !");
}
@GetMapping("/findPreferAuthorByName/{iduser}")
@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
public ResponseEntity<List<AuthorDTO>> findPreferAuthorByName(@PathVariable("iduser") int user_id, @RequestParam("name") String name){
	if(authorService.findPreferAuthorByName(user_id,name) != null){
		List<Author> authors = authorService.findPreferAuthorByName(user_id,name);
		return new ResponseEntity<List<AuthorDTO>>(authorConverter.entitiesToDTOs(authors), HttpStatus.OK);
	}else
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
}

}
	