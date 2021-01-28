package com.project.services;

import java.util.List;


import com.project.entities.Book;
import com.project.entities.Wishlist;


public interface WishlistService {
	
	public List<Wishlist> addWishlist(long idUser,String name);
	
	public boolean removeWishlist(long idWishlist);
	
	public Wishlist clearWishlist(long idWishlist);
	
	public Wishlist addBookToWishlist(long idWishlist, long idBook);
	
	public Wishlist removeBookFromWishlist(long idWishlist, long idBook);
	
	public List<Wishlist> getAllWishlistsByUser(long id);
	
	public List<Book> getAllBooksInWishlist(long id);
	
	public Wishlist updateWishlistName(long idWishlist,String name);

}
