package com.project.controllers;
import java.util.List;
import java.util.Objects;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.DTOs.AuthorDTO;
import com.project.DTOs.SerieDTO;
import com.project.converter.AuthorConverter;
import com.project.entities.Author;
import com.project.entities.Serie;
import com.project.services.AuthorService;
import com.project.services.AuthorServiceImpl;



@RestController
public class AuthorController {
@Autowired 
AuthorServiceImpl authorService ;
@Autowired
AuthorConverter authorConverter ;
	@PostMapping("/addAuthor")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO){
		Author author = authorConverter.DTOToentity(authorDTO);
		authorService.addAuthor(author);
		if(Objects.isNull(author.getId()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		return  new ResponseEntity<AuthorDTO>(authorConverter.entityToDTO(author), HttpStatus.OK);
	}
	
	@PutMapping(value="/updateAuthor")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> updateSerie(@RequestBody AuthorDTO authordto){
		Author author = authorConverter.DTOToentity(authordto);
		authorService.addAuthor(author);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Updated Successfully !");
	}
	@GetMapping("/getAuthors")
	public List<Author> getAuthors(){
		return authorService.getAuthors();
	}
	@DeleteMapping("/deleteById/{idauthor}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteById(@PathVariable("idauthor") long authorid){
		authorService.deleteById(authorid);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("auteur supprime");
		}
		// http://localhost:8082/MintyBook/servlet/findAuthorById
			@PostMapping("/findAuthorByid/{idauthor}")
			public void findAuthorById(@PathVariable("idauthor") long idauthor) {
			authorService.findAuthorById(idauthor);
				
				        
	}
}
