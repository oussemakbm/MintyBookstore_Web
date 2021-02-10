package com.project.services;

import java.util.List;

import com.project.DTOs.BookDetailDTO;
import com.project.entities.Book;

public interface BookService {
	
	/*** C R U D***/
	public Book addOrUpdateBook(BookDetailDTO book);
	public boolean deleteBook(long id);
	public Book findBookById(long id);
	public List<Book> getBooks();

	/***  Prix  ***/
	public List<Book> findByPrixLessThanEqual(float p);
	public List<Book> findByPrixBetween(float p1, float p2);
	public List<Book> findByPrixGreaterThan(float p);

	/***  Quantity  ***/
	public List<Book> findNotAvailable();
	public List<Book> findByQuantityLessThanEqual(long q);
	public List<Book> findByQuantityBetween(long q1, long q2);
	public List<Book> findByQuantityGreaterThan(long q);
	public boolean updateQuantity(long id,long q);

	/***  Rating  ***/
	public List<Book> findByRatingLessThanEqual(long r);
	public List<Book> findByRatingBetween(long r1, long r2);
	public List<Book> findByRatingGreaterThan(long r);
	public List<Book> findByRatingEquals(long r);

	/***   Book (Titre, Category, Author, Langue, Serie)   ***/
	public List<Book> getBooksByTitre(String name);
	public List<Book> getBooksByCategory(long id);
	public List<Book> getBooksByAuthor(long id);
	public List<Book> getBooksByLangue(long id);
	public List<Book> getBooksBySerie(long id);
	
	/***  BestBooks (Rating) - BestBooks (Sellers) - BestBooks (Cheapest) - Most Wishted Books  ***/
	public List<Book> getBestBooksByYear(String year);
	public List<Book> getBestBooksByMonth(String month);
	public List<Book> getBestBooksEver();
	public List<Book> getBestBooksSellersByYear(String year);
	public List<Book> getBestBooksSellersByMonth(String month);
	public List<Book> getBestSellersEver();
	public List<Book> getCheapestBooks();
	public List<Book> getMostWantedBooks();
	public List<Book> getMostInteractiveBooks();
	public List<Book> getMostLovedBooks();
}
