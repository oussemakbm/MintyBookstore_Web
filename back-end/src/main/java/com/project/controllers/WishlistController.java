package com.project.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.RemoveBookDTO;
import com.project.entities.Book;
import com.project.entities.Wishlist;
import com.project.services.WishlistService;

@RestController
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@DeleteMapping("/api/wishlist/removebook")
	public ResponseEntity<Wishlist> removeBookFromWishlist(@RequestBody RemoveBookDTO removeBookDTO) {
		Wishlist w=wishlistService.removeBookFromWishlist(removeBookDTO.getIdWishlist(), removeBookDTO.getIdBook());
		return new ResponseEntity<Wishlist>(w,HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/wishlist/all/{id}")
	public ResponseEntity<List<Wishlist>> getAllWishlistsByUser(@RequestParam("id") long idUser) {
		List<Wishlist> w=wishlistService.getAllWishlistsByUser(idUser);
		return new ResponseEntity<List<Wishlist>>(w,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/wishlist/getbooks/{id}")
	public ResponseEntity<List<Book>> getAllBooksInWishlist(@RequestParam("id") long idWishlist) {
		List<Book> books=wishlistService.getAllBooksInWishlist(idWishlist);
		return new ResponseEntity<List<Book>>(books,HttpStatus.ACCEPTED);
	}
	
}
