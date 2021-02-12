package com.project.services;

import java.util.List;

import com.project.DTOs.BookDTO;
import com.project.DTOs.SuggestDTO;
import com.project.DTOs.WishlistDTO;


public interface WishlistService {
	
	public List<WishlistDTO> addWishlist(long idUser,String name);
	
	public boolean removeWishlist(long idWishlist);
	
	public WishlistDTO clearWishlist(long idWishlist);
	
	public WishlistDTO addBookToWishlist(long idWishlist, long idBook);
	
	public WishlistDTO removeBookFromWishlist(long idWishlist, long idBook);
	
	public List<WishlistDTO> getAllWishlistsByUser(long id);
	
	public List<BookDTO> getAllBooksInWishlist(long id);
	
	public WishlistDTO updateWishlistName(long idWishlist,String name);
	
	public List<SuggestDTO> suggestBooks();

}
