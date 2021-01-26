package com.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.User;
import com.project.entities.Wishlist;

@Service
public interface WishlistService {
	
	
	public Wishlist removeBookFromWishlist(long idWishlist, long idBook);
	
	public List<Wishlist> getAllWishlistsByUser(long id);
	
	public List<Book> getAllBooksInWishlist(long id);

}
