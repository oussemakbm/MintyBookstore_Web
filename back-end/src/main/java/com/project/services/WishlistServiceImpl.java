package com.project.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.User;
import com.project.entities.Wishlist;
import com.project.repos.BookRepo;
import com.project.repos.UserRepo;
import com.project.repos.WishlistRepo;


@Service
public class WishlistServiceImpl implements WishlistService{
	
	@Autowired
	WishlistRepo wr;
	
	@Autowired 
	BookRepo br;
	
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
		w=wr.save(w);
		return w;
	}

	@Override
	public List<Wishlist> getAllWishlistsByUser(long id) {
		return ur.findById(id).get().getWishlists();
	}

	@Override
	public List<Book> getAllBooksInWishlist(long id) {
		Wishlist w=wr.findById(id).get();
		return w.getBooks();
	}

	@Override
	public Wishlist addBookToWishlist(long idWishlist, long idBook) {
		Book b=br.findById(idBook).get();
		Wishlist w=wr.findById(idWishlist).get();
		w.getBooks().add(b);
		w=wr.save(w);
		return w;
	}

	@Override
	public Wishlist updateWishlistName(long idWishlist, String name) {
		Wishlist w=wr.findById(idWishlist).get();
		w.setName(name);
		w=wr.save(w);
		return w;
	}

	@Override
	public List<Wishlist> addWishlist(long idUser, String name) {
			User u=ur.findById(idUser).get();
			Wishlist w=new Wishlist();
			w.setName(name);
			w.setUser(u);
			wr.save(w);
		return u.getWishlists();
	}

	@Override
	public boolean removeWishlist(long idWishlist) {
		if (wr.findById(idWishlist).get() instanceof Wishlist) {
			wr.deleteById(idWishlist);
			return true;
		}
		return false;
	}

	@Override
	public Wishlist clearWishlist(long idWishlist) {
		Wishlist w=wr.findById(idWishlist).get();
		w.getBooks().clear();
		return wr.save(w);
	}
}
