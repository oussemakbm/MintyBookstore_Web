package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.Book;
import com.project.entities.Wishlist;
import com.project.repos.UserRepo;
import com.project.repos.WishlistRepo;

public class WishlistServiceImpl implements WishlistService{
	
	@Autowired
	WishlistRepo wr;
	
	@Autowired 
	UserRepo ur;
	
	@Override
	public Wishlist removeBookFromWishlist(long idWishlist, long idBook ) {
		Wishlist w=wr.findById(idWishlist).get();
		for (Book book : w.getBooks()) {
			if (book.getId()==idBook) {
				w.getBooks().remove(book);
				break;
			}
		}
		wr.save(w);
		return w;
	}

	@Override
	public List<Wishlist> getAllWishlistsByUser(long id) {
		
		return null;
	}

	@Override
	public List<Book> getAllBooksInWishlist(long id) {
		Wishlist w=wr.findById(id).get();
		return w.getBooks();
	}
}
