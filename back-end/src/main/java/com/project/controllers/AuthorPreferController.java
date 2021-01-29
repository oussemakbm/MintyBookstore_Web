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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.entities.Author;
import com.project.entities.Serie;
import com.project.services.AuthorPreferService;

@RestController
public class AuthorPreferController {
@Autowired 
AuthorPreferService authorPreferServ ; 
// http://localhost:8081/MintyBook/servlet/addAuthorPrefer
@PostMapping("/addAuthorPrefer/{iduser}/{idauthor}")
@ResponseBody
public void addAuthorPrefer(@PathVariable("iduser") Long user_id, @PathVariable("idauthor") Long author_id){
	authorPreferServ.addAuthorPrefer(user_id,author_id);
}
	
// http://localhost:8081/MintyBook/servlet/deleteAuthorPrefer
@PutMapping(value="/deleteAuthorPrefer")
@ResponseBody
public void deleteAuthorPrefer(@PathVariable("iduser") Long user_id, @PathVariable("idauthor") Long author_id){
	authorPreferServ.deleteAuthorPrefer(user_id,author_id);
}
	
// http://localhost:8081/MintyBook/servlet/getAllPreferAuthor
@PostMapping("/getAllPreferAuthor/{iduser}")
@ResponseBody
public List<Author> getAllPreferAuthor(@PathVariable("iduser") Long user_id){
	return authorPreferServ.getAllPreferAuthor(user_id);
}
	

// http://localhost:8081/MintyBook/servlet/getPreferAuthor
@PostMapping("/getPreferAuthor")
@ResponseBody
public Author getPreferAuthor(@PathVariable("iduser") Long user_id, @PathVariable("idauthor") Long author_id){
	return authorPreferServ.getPreferAuthor(user_id,author_id);
}


}
	