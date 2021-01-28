package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.services.BookServiceImpl;

import com.project.entities.Book;


@RestController
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
	
	// http://localhost:8081/MintyBook/servlet/addBook
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
		return book;
	}
	
	// http://localhost:8081/MintyBook/servlet/updateBook
	@PutMapping(value="/updateBook")
	public void updateBook(@RequestBody Book book){
		bookservice.addOrUpdateBook(book);
	}
	
	// http://localhost:8081/MintyBook/servlet/getBooks
	@PostMapping("/getBooks")
	public List<Book> getBooks(){
		return bookservice.getBooks();
	}
	
	// http://localhost:8081/MintyBook/servlet/deleteBook
	@DeleteMapping("/deleteBook/{idbook}")
	public void deleteBook(@PathVariable("idbook") long bookid){
		bookservice.deleteBook(bookid);
	}

}
