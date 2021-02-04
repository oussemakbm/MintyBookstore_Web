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
import com.project.DTOs.RemoveBookFromWishlistDTO;
import com.project.entities.Book;
import com.project.entities.Wishlist;
import com.project.services.WishlistService;

@RestController
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@DeleteMapping("/api/wishlist/removebook")
	public ResponseEntity<Wishlist> removeBookFromWishlist(@RequestBody RemoveBookFromWishlistDTO removeBookDTO) {
		Wishlist w=wishlistService.removeBookFromWishlist(removeBookDTO.getIdWishlist(), removeBookDTO.getIdBook());
		if (w!=null)
			return new ResponseEntity<Wishlist>(w,HttpStatus.OK);
		return new ResponseEntity<Wishlist>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/api/wishlist/remove/{id}")
	public ResponseEntity<Boolean> removeWishlist(@RequestParam("id") long id) {
		boolean w=wishlistService.removeWishlist(id);
		if (w)
			return new ResponseEntity<Boolean>(w,HttpStatus.OK);
		return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/api/wishlist/clear/{id}")
	public ResponseEntity<Wishlist> clearWishlist(@RequestParam("id") long idWishlist) {
		Wishlist w=wishlistService.clearWishlist(idWishlist);
		if (w!=null)
			return new ResponseEntity<Wishlist>(w,HttpStatus.OK);
		return new ResponseEntity<Wishlist>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/wishlist/add/{id}/{name}")
	public ResponseEntity<List<Wishlist>> addWishlist(@PathVariable("id") long idUser,@PathVariable("name") String name) {
		List<Wishlist> w=wishlistService.addWishlist(idUser, name);
		System.out.println(idUser);
		if (w!=null)
			return new ResponseEntity<List<Wishlist>>(w,HttpStatus.OK);
		return new ResponseEntity<List<Wishlist>>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/api/wishlist/all/{id}")
	public ResponseEntity<List<Wishlist>> getAllWishlistsByUser(@RequestParam("id") long idUser) {
		List<Wishlist> w=wishlistService.getAllWishlistsByUser(idUser);
		if (w!=null)
			return new ResponseEntity<List<Wishlist>>(w,HttpStatus.OK);
		return new ResponseEntity<List<Wishlist>>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/wishlist/addbook")
	public ResponseEntity<Wishlist> addBookToWishlist(@RequestBody AddBookToWishlistDTO AddBookdto) {
		Wishlist w=wishlistService.addBookToWishlist(AddBookdto.getIdWishlist(),AddBookdto.getIdBook());
		if (w!=null)
			return new ResponseEntity<Wishlist>(w,HttpStatus.OK);
		return new ResponseEntity<Wishlist>(HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/api/wishlist/getbooks/{id}")
	public ResponseEntity<List<Book>> getAllBooksInWishlist(@RequestParam("id") long idWishlist) {
		List<Book> books=wishlistService.getAllBooksInWishlist(idWishlist);
		if (books!=null)
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		return new ResponseEntity<List<Book>>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/api/wishlist/update/{id}/{name}")
	public ResponseEntity<Wishlist> updateWishlistName(@PathVariable("id") long idWishlist,@PathVariable("name") String name){
		Wishlist w=wishlistService.updateWishlistName(idWishlist,name);
		if (w!=null)
			return new ResponseEntity<Wishlist>(w,HttpStatus.OK);
		return new ResponseEntity<Wishlist>(HttpStatus.BAD_REQUEST);
	}
}
