package com.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTOs.BookDTO;
import com.project.DTOs.SuggestDTO;
import com.project.DTOs.WishlistDTO;
import com.project.converter.BookConverter;
import com.project.converter.WishlistConverter;
import com.project.entities.Book;
import com.project.entities.User;
import com.project.entities.Wishlist;
import com.project.repos.BookRepo;
import com.project.repos.UserRepo;
import com.project.repos.WishlistRepo;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	WishlistRepo wr;

	@Autowired
	BookRepo br;

	@Autowired
	UserRepo ur;
	
	@Autowired
	BookConverter bookConverter;
	
	@Autowired
	WishlistConverter wishlistConverter;

	@Override
	public WishlistDTO removeBookFromWishlist(long idWishlist, long idBook) {
		Wishlist w = wr.findById(idWishlist).get();
		Book book=br.findById(idBook).get();
		for (Wishlist wishlist : book.getWishlists()) {
			if (wishlist.getId() == idWishlist) {
				book.getWishlists().remove(wishlist);
				break; 
			}
		}
		br.save(book);
		w = wr.save(w);
		return wishlistConverter.entityToDTO(w);
	}

	@Override
	public List<WishlistDTO> getAllWishlistsByUser(long id) {
		return wishlistConverter.entitiesToDTOs(ur.findById(id).get().getWishlists()); 
	}

	@Override
	public List<BookDTO> getAllBooksInWishlist(long id) {
		if (!wr.existsById(id)) return null;
		Wishlist w = wr.findById(id).get();
		return bookConverter.entitiesToDTOs(w.getBooks());
	}

	@Override
	public WishlistDTO addBookToWishlist(long idWishlist, long idBook) {
		if (!(br.existsById(idBook)) || !(wr.existsById(idWishlist))) return null;
		Book b = br.findById(idBook).get();
		Wishlist w = wr.findById(idWishlist).get();
		if (!b.getWishlists().contains(w))
			b.getWishlists().add(w);
		
		//System.out.println(w.getBooks().get(0));
		br.save(b);
		w = wr.save(w);
		return wishlistConverter.entityToDTO(w);
	}

	@Override
	public WishlistDTO updateWishlistName(long idWishlist, String name) {
		if (!wr.existsById(idWishlist)) return null;
		Wishlist w = wr.findById(idWishlist).get();
		w.setName(name);
		w = wr.save(w);
		return wishlistConverter.entityToDTO(w);
	}

	@Override
	public List<WishlistDTO> addWishlist(long idUser, String name) {
		User u = ur.findById(idUser).get();
		Wishlist w = new Wishlist();
		w.setName(name);
		w.setUser(u);
		w.setBooks(new ArrayList<>());
		wr.save(w);
		return wishlistConverter.entitiesToDTOs(u.getWishlists());
	}

	@Override
	public boolean removeWishlist(long idWishlist) {
		if (wr.existsById(idWishlist)) {
			for (Book b :wr.findById(idWishlist).get().getBooks()) {
				removeBookFromWishlist(idWishlist, b.getId());
			}
			wr.deleteById(idWishlist);
			return true;
		}
		return false;
	}

	@Override
	public WishlistDTO clearWishlist(long idWishlist) {
		if (!wr.existsById(idWishlist)) return null;
		Wishlist w = wr.findById(idWishlist).get();
		w.getBooks().clear();
		return wishlistConverter.entityToDTO(wr.save(w));
	}

	@Override
	public List<SuggestDTO> suggestBooks() {
		List<User> users = (List<User>) ur.findAll();
		List<Book> books = (List<Book>) br.findAll();
		List<SuggestDTO> suggestedBooks=new ArrayList<>();
		
		for (Book currentBook : books) {
			int occurence=0;
			for (User currentUser : users) {
				for (Wishlist currentWishlist : currentUser.getWishlists()) {
					if (existInWishlist(currentWishlist, currentBook)) {
						occurence++;
						break;
					}
				}
			}
			SuggestDTO book=new SuggestDTO();
			
			book.setBook(bookConverter.entityToDTO(currentBook));
			book.setNbUsers(occurence);
			suggestedBooks.add(book);
		}
		
		suggestedBooks=suggestedBooks.stream()
				.sorted((a,b)->b.getNbUsers()-a.getNbUsers()).limit(2).collect(Collectors.toList());
		return suggestedBooks;
	}
 
	public boolean existInWishlist(Wishlist wishlist, Book book) {
		for (Book b : wishlist.getBooks()) {
			if (b.getId() == book.getId())
				return true;
		}
		return false;
	}
}
