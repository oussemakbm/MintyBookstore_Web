package com.project.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	static List<Book> books;
	/*** C - R - U - D ***/
	@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<BookDetailDTO> addBook(@RequestBody BookDetailDTO bookdto){
		if(bookservice.findBooksByTitre(bookdto.getTitle()))
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
	public ResponseEntity<List<BookDTO>> getBooks(){
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

	/*** Filtrage By Entity ***/
	@GetMapping("/getBooksByTitre")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksByTitre(@RequestParam("name") String name){
		if(bookservice.getBooksByTitre(name) != null){
			List<Book> books = bookservice.getBooksByTitre(name);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBooksByLangue/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksByLangue(@PathVariable("id") long id){
		if(bookservice.getBooksByLangue(id) != null){
			List<Book> books = bookservice.getBooksByLangue(id);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBooksByCategory/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksByCategory(@PathVariable("id") long id){
		if(bookservice.getBooksByCategory(id) != null){
			List<Book> books = bookservice.getBooksByCategory(id);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBooksByAuthor/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable("id") long id){
		if(bookservice.getBooksByAuthor(id) != null){
			List<Book> books = bookservice.getBooksByAuthor(id);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@GetMapping("/getBooksBySerie/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBooksBySerie(@PathVariable("id") long id){
		if(bookservice.getBooksBySerie(id) != null){
			List<Book> books = bookservice.getBooksBySerie(id);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	/*** Filtrage PRIX ***/
	@GetMapping("/findByPrixLessThanEqual")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByPrixLessThanEqual(@RequestParam("p") float p) {
		if(bookservice.findByPrixLessThanEqual(p) != null){
			books = bookservice.findByPrixLessThanEqual(p);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByPrixBetween")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByPrixBetween(@RequestParam("p1") float p1, @RequestParam("p2") float p2) {
		if(bookservice.findByPrixBetween(p1,p2) != null){
			List<Book> books = bookservice.findByPrixBetween(p1,p2);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByPrixGreaterThan")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByPrixGreaterThan(@RequestParam("p") float p) {
		if(bookservice.findByPrixGreaterThan(p) != null){
			List<Book> books = bookservice.findByPrixGreaterThan(p);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
	}
	
	/***  Quantity  ***/
	@GetMapping("/findNotAvailable")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findNotAvailable(){
		if(bookservice.findNotAvailable() != null){
			List<Book> books = bookservice.findNotAvailable();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByQuantityLessThanEqual")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByQuantityLessThanEqual(@RequestParam("q") long q) {
		if(bookservice.findByQuantityLessThanEqual(q) != null){
			List<Book> books = bookservice.findByQuantityLessThanEqual(q);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
	}

	@GetMapping("/findByQuantityBetween")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByQuantityBetween(@RequestParam("q1") long q1, @RequestParam("q2") long q2) {
		if(bookservice.findByQuantityBetween(q1,q2) != null){
			List<Book> books = bookservice.findByQuantityBetween(q1,q2);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByQuantityGreaterThan")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByQuantityGreaterThan(@RequestParam("q") long q) {
		if(bookservice.findByQuantityGreaterThan(q) != null){
			List<Book> books = bookservice.findByQuantityGreaterThan(q);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
	}
	
	@PutMapping("/addQuantity/{bookid}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<String> addQuantity(@PathVariable("bookid") long id, @RequestParam("q") long q){
		if(bookservice.addQuantity(id,q)){
			return ResponseEntity.status(HttpStatus.OK).body("Added Successfully");
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Added Failed");
	}

	/***  Rating  ***/
	@GetMapping("/findByRatingLessThanEqual")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByRatingLessThanEqual(@RequestParam("r") long r) {
		if(bookservice.findByRatingLessThanEqual(r) != null){
			List<Book> books = bookservice.findByRatingLessThanEqual(r);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByRatingBetween")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByRatingBetween(@RequestParam("r1") long r1, @RequestParam("r2") long r2) {
		if(bookservice.findByRatingBetween(r1,r2) != null){
			List<Book> books = bookservice.findByRatingBetween(r1,r2);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
	}

	@GetMapping("/findByRatingGreaterThan")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByRatingGreaterThan(@RequestParam("r") long r) {
		if(bookservice.findByRatingGreaterThan(r) != null){
			List<Book> books = bookservice.findByRatingGreaterThan(r);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/findByRatingEquals")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> findByRatingEquals(@RequestParam("r") long r) {
		if(bookservice.findByRatingEquals(r) != null){
			List<Book> books = bookservice.findByRatingEquals(r);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}
	
	/***  BestBooks (Rating) - BestBooks (Sellers) - BestBooks (Cheapest) - Most Wishted Books  ***/
	
	@GetMapping("/getBestBooksByYear")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestBooksByYear(@RequestParam("year") String year) {
		if(bookservice.getBestBooksByYear(year) != null){
			List<Book> books = bookservice.getBestBooksByYear(year);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/getBestBooksByMonth")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestBooksByMonth(@RequestParam("month") String month) {
		if(bookservice.getBestBooksByMonth(month) != null){
			books = bookservice.getBestBooksByMonth(month);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	}

	@GetMapping("/getBestBooksEver")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestBooksEver() {
		if(bookservice.getBestBooksEver() != null){
			books = bookservice.getBestBooksEver();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getBestBooksSellersByYear")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestBooksSellersByYear(@RequestParam("year") String year) {
		if(bookservice.getBestBooksSellersByYear(year) != null){
			books = bookservice.getBestBooksSellersByYear(year);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getBestBooksSellersByMonth")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestBooksSellersByMonth(@RequestParam("month") String month) {
		if(bookservice.getBestBooksSellersByMonth(month) != null){
			books = bookservice.getBestBooksSellersByMonth(month);
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getBestSellersEver")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getBestSellersEver() {
		if(bookservice.getBestSellersEver() != null){
			books = bookservice.getBestSellersEver();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getCheapestBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getCheapestBooks() {
		if(bookservice.getCheapestBooks() != null){
			books = bookservice.getCheapestBooks();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);	
	}

	@GetMapping("/getMostWantedBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getMostWantedBooks() {
		if(bookservice.getMostWantedBooks() != null){
			books = bookservice.getMostWantedBooks();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getMostInteractiveBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getMostInteractiveBooks(){
		if(bookservice.getMostInteractiveBooks() != null){
			books = bookservice.getMostInteractiveBooks();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getMostLovedBooks")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<List<BookDTO>> getMostLovedBooks(){
		if(bookservice.getMostLovedBooks() != null){
			books = bookservice.getMostLovedBooks();
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books), HttpStatus.OK);
		}else
			return new ResponseEntity<List<BookDTO>>(bookConverter.entitiesToDTOs(books),HttpStatus.NO_CONTENT);
	}
}
