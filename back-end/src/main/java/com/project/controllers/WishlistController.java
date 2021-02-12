package com.project.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.AddBookToWishlistDTO;
import com.project.DTOs.BookDTO;
import com.project.DTOs.RemoveBookFromWishlistDTO;
import com.project.DTOs.SuggestDTO;
import com.project.DTOs.WishlistDTO;
import com.project.entities.Book;
import com.project.entities.Wishlist;
import com.project.services.WishlistService;

@RestController
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@DeleteMapping("/wishlist/removebook")
	public ResponseEntity<?> removeBookFromWishlist(@RequestBody RemoveBookFromWishlistDTO removeBookDTO) {
		WishlistDTO w=wishlistService.removeBookFromWishlist(removeBookDTO.getIdWishlist(), removeBookDTO.getIdBook());
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@DeleteMapping("/wishlist/remove/{id}")
	public ResponseEntity<?> removeWishlist(@PathVariable("id") long id) {
		boolean w=wishlistService.removeWishlist(id);
		if (w)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@DeleteMapping("/wishlist/clear/{id}")
	public ResponseEntity<?> clearWishlist(@PathVariable("id") long idWishlist) {
		WishlistDTO w=wishlistService.clearWishlist(idWishlist); 
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@PostMapping("/wishlist/add/{id}/{name}")
	public ResponseEntity<?> addWishlist(@PathVariable("id") long idUser,@PathVariable("name") String name) {
		List<WishlistDTO> w=wishlistService.addWishlist(idUser, name);
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("There has been an error while processing the opration!");

	}

	@GetMapping("/wishlist/all/{id}")
	public ResponseEntity<?> getAllWishlistsByUser(@PathVariable("id") long idUser) {
		List<WishlistDTO> w=wishlistService.getAllWishlistsByUser(idUser);
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@PostMapping("/wishlist/addbook")
	public ResponseEntity<?> addBookToWishlist(@RequestBody AddBookToWishlistDTO AddBookdto) {
		WishlistDTO w=wishlistService.addBookToWishlist(AddBookdto.getIdWishlist(),AddBookdto.getIdBook());
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("There has been an error while processing the opration!");

	}
	
	@GetMapping("/wishlist/getbooks/{id}")
	public ResponseEntity<?> getAllBooksInWishlist(@PathVariable("id") long idWishlist) {
		List<BookDTO> books=wishlistService.getAllBooksInWishlist(idWishlist);
		if (books!=null)
			return ResponseEntity.ok().body(books);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@PutMapping("/wishlist/update/{id}/{name}")
	public ResponseEntity<?> updateWishlistName(@PathVariable("id") long idWishlist,@PathVariable("name") String name){
		WishlistDTO w=wishlistService.updateWishlistName(idWishlist,name);
		if (w!=null)
			return ResponseEntity.ok().body(w);
		return ResponseEntity.badRequest().body("Wishlist does not exist !");
	}
	
	@GetMapping("/wishlist/suggestbooks/{id}")
	public ResponseEntity<?> suggestBooks(@PathVariable("id") long id){
		List<SuggestDTO> suggestedBooks=wishlistService.suggestBooks();
		return ResponseEntity.ok().body(suggestedBooks);
	}
	
}
