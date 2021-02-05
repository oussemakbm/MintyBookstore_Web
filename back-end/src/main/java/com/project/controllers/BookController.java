package com.project.controllers;

import java.util.List;
import java.util.Objects;

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
import com.project.DTOs.BookDTO;
import com.project.DTOs.BookDetailDTO;
import com.project.converter.BookConverter;
import com.project.entities.Book;


@RestController
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
	@Autowired
	BookConverter bookConverter;
	
	@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
		if(Objects.isNull(book.getId()))
			return new ResponseEntity<Book>(book, HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void updateBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
	}
	
	@GetMapping("/getBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public List<Book> getBooksToAdmin(){
		return bookservice.getBooks();
	}
	
	@GetMapping("/getBook/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<BookDTO> getBook(@PathVariable("idbook") long bookid){
		Book book = bookservice.findBookById(bookid);
		return new ResponseEntity<BookDTO>(bookConverter.entityToDTO(book), HttpStatus.OK);
	}
	
	@GetMapping("/getBookDetail/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<BookDetailDTO> getBookDetail(@PathVariable("idbook") long bookid){
		Book book = bookservice.findBookById(bookid);
		return new ResponseEntity<BookDetailDTO>(bookConverter.entityToDetailDTO(book), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteBook/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void deleteBook(@PathVariable("idbook") long bookid){
		bookservice.deleteBook(bookid);
	}

}
