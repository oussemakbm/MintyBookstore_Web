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
import com.project.DTOs.SerieDTO;
import com.project.converter.BookConverter;
import com.project.entities.Book;
import com.project.entities.Serie;


@RestController
@RequestMapping(value="/book")
public class BookController {
	
	@Autowired
	BookServiceImpl bookservice;
	@Autowired
	BookConverter bookConverter;
	
	@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<BookDetailDTO> addBook(@RequestBody BookDetailDTO bookdto){
		if(bookservice.findBookByTitre(bookdto.getTitle()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		Book book = bookservice.addOrUpdateBook(bookdto);
		if(book == null || Objects.isNull(book.getId()))
			return new ResponseEntity<BookDetailDTO>(bookConverter.entityToDetailDTO(book), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<BookDetailDTO>(bookConverter.entityToDetailDTO(book), HttpStatus.OK);
	}
	
	@PutMapping(value="/updateBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> updateBook(@RequestBody BookDetailDTO bookdto){
		Book book = bookservice.addOrUpdateBook(bookdto);
		if(book == null || Objects.isNull(book.getId()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Updated failed !");
		return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully !");
	}
	
	@GetMapping("/getBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksToAdmin(){
		if(bookservice.getBooks() != null){
			List<Book> books = bookservice.getBooks();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBook/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<BookDTO> getBook(@PathVariable("idbook") long bookid){
		Book book = bookservice.findBookById(bookid);
		if(book != null)
			return new ResponseEntity<BookDTO>(bookConverter.entityToDTO(book), HttpStatus.OK);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBookDetail/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<BookDetailDTO> getBookDetail(@PathVariable("idbook") long bookid){
		Book book = bookservice.findBookById(bookid);
		if(book != null)
			return new ResponseEntity<BookDetailDTO>(bookConverter.entityToDetailDTO(book), HttpStatus.ACCEPTED);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@DeleteMapping("/deleteBook/{idbook}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteBook(@PathVariable("idbook") long bookid){
		bookservice.deleteBook(bookid);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");
	}

}
