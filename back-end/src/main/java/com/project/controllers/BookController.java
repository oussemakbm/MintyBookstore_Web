package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.project.services.BookServiceImpl;

import com.project.entities.Book;
import com.project.entities.Comment;


@RestController
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
	
	@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
		return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/updateBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void updateBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
	}
	
	@GetMapping("/admin/getBooks")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<Book> getBooksToAdmin(){
		return bookservice.getBooks();
	}
	
	@GetMapping("/client/getBooks")
	@PreAuthorize("hasAnyRole('CLIENT')")
	public List<Book> getBooksToClient(){
		return bookservice.getBooks();
	}
	
	@DeleteMapping("/deleteBook/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void deleteBook(@PathVariable("idbook") long bookid){
		bookservice.deleteBook(bookid);
	}

}
