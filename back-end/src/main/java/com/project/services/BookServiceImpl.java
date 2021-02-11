package com.project.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTOs.BookDTOA;
import com.project.DTOs.BookDetailDTO;
import com.project.converter.BookConverter;
import com.project.entities.Author;
import com.project.entities.Book;
import com.project.entities.Category;
import com.project.entities.Langue;
import com.project.entities.Serie;
import com.project.repos.AuthorRepo;
import com.project.repos.BookRepo;
import com.project.repos.CategoryRepo;
import com.project.repos.CommandLineRepo;
import com.project.repos.InteractionRepo;
import com.project.repos.LanguageRepo;
import com.project.repos.SerieRepo;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepo bookRepo;
	@Autowired
	AuthorRepo authorRepo;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	SerieRepo serieRepo;
	@Autowired
	LanguageRepo langueRepo;
	@Autowired
	BookConverter bookConverter;
	@Autowired
	InteractionRepo interRepo;
	@Autowired
	CommandLineRepo clineRepo;
	
	/*** C R U D ***/
	@Override
	public Book addOrUpdateBook(BookDTOA bookdtoa) {
		Book book =bookConverter.DTOAToentity(bookdtoa);
		if(!(authorRepo.existsById(bookdtoa.getAuthorID()) && categoryRepo.existsById(bookdtoa.getCategoryID()) &&
			langueRepo.existsById(bookdtoa.getLanguageID())))
			return null;
		
		if(!Objects.isNull(bookdtoa.getSerieID()))
			book.setSerie(null);
		else{
			if(serieRepo.existsById(bookdtoa.getSerieID()))
				return null;
			Serie s = serieRepo.findById(bookdtoa.getSerieID()).get();
			book.setSerie(s);
		}
		Author a = authorRepo.findById(bookdtoa.getAuthorID()).get();
		book.setAuthor(a);
		Category c = categoryRepo.findById(bookdtoa.getCategoryID()).get();
		book.setCategory(c);
		Langue l = langueRepo.findById(bookdtoa.getLanguageID()).get();
		book.setLanguage(l);
		bookRepo.save(book);
		return book;
	}

	@Override
	public boolean deleteBook(long id) {
		if(!(bookRepo.existsById(id)))
			return false;
		bookRepo.deleteById(id);
		return true;
	}
	
	@Override
	public Book findBookById(long id) {
		if(!(bookRepo.existsById(id)))
			return null;
		return bookRepo.findById(id).get();
	}

	@Override
	public List<Book> getBooks() {
		return (List<Book>) bookRepo.findAll();
	}
	
	public List<Book> getBooksByTitre(String name){
		List<Book> books =  (List<Book>) bookRepo.findAll();
		return books.stream().filter(b -> b.getTitle().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
	}

	/***   Book (Titre, Category, Author, Langue, Serie)   ***/
	public boolean findBooksByTitre(String name){
		if( 0 == bookRepo.findBooksByTitre(name))
			return false;
		return true;
	}
	
	@Override
	public List<Book> getBooksByCategory(long id) {
		return bookRepo.getBooksByCategory(id);
	}

	@Override
	public List<Book> getBooksByAuthor(long id) {
		return bookRepo.getBooksByAuthor(id);
	}

	@Override
	public List<Book> getBooksByLangue(long id) {
		return bookRepo.getBooksByLangue(id);
	}

	@Override
	public List<Book> getBooksBySerie(long id) {
		return bookRepo.getBooksBySerie(id);
	}
	
	/***  Prix  ***/
	@Override
	public List<Book> findByPrixLessThanEqual(float p) {
		return bookRepo.findByPrixLessThanEqual(p);
	}

	@Override
	public List<Book> findByPrixBetween(float p1, float p2) {
		return bookRepo.findByPrixBetween(p1,p2);
	}

	@Override
	public List<Book> findByPrixGreaterThan(float p) {
		return bookRepo.findByPrixGreaterThan(p);
	}
	
	/***  Quantity  ***/
	@Override
	public List<Book> findNotAvailable(){
		return bookRepo.findNotAvailable();
	}

	@Override
	public List<Book> findByQuantityLessThanEqual(long q) {
		return bookRepo.findByQuantityLessThanEqual(q);
	}

	@Override
	public List<Book> findByQuantityBetween(long q1, long q2) {
		return bookRepo.findByQuantityBetween(q1,q2);
	}

	@Override
	public List<Book> findByQuantityGreaterThan(long q) {
		return bookRepo.findByQuantityGreaterThan(q);
	}
	
	@Transactional
	public boolean updateQuantity(long id,long q){
		if(this.isAvailable(id,q)){
			if(bookRepo.updateQuantity(id,q)>0)
				return true;
		}
		return false;
	}
	
	public boolean isAvailable(long id,long q){
		if(bookRepo.existsById(id)){
			Book b = bookRepo.findById(id).get();
			return (b.getQuantity() + q)>= 0;
		}
		return false;
	}

	
	/***  Rating  ***/
	@Override
	public List<Book> findByRatingLessThanEqual(long r) {
		return bookRepo.findByRatingLessThanEqual(r);
	}

	@Override
	public List<Book> findByRatingBetween(long r1, long r2) {
		return bookRepo.findByRatingBetween(r1,r2);
	}

	@Override
	public List<Book> findByRatingGreaterThan(long r) {
		return bookRepo.findByRatingGreaterThan(r);
	}

	@Override
	public List<Book> findByRatingEquals(long r) {
		return bookRepo.findByRatingEquals(r);
	}
	
	/***  BestBooks (Rating) - BestBooks (Sellers) - BestBooks (Cheapest) - Most Wishted Books  ***/
	@Override
	public List<Book> getBestBooksByYear(String year) {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.filter(b -> b.getPublishDate().substring(6,10).equals(year))
				.limit(20).collect(Collectors.toList());
	}

	@Override
	public List<Book> getBestBooksByMonth(String month) {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.filter(b -> b.getPublishDate().substring(0,2).equals(month))
				.limit(20).collect(Collectors.toList());
	}

	@Override
	public List<Book> getBestBooksEver() {
		return bookRepo.findTop20ByOrderByRatingDesc();
	}

	@Override
	public List<Book> getBestBooksSellersByYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBestBooksSellersByMonth(String month) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Book> getBestSellersEver() {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream().filter(b -> clineRepo.getNumberOfPurchases(b.getId()) != null)
				.sorted((b1,b2) -> {
					Long obj1 = clineRepo.getNumberOfPurchases(b1.getId()); 
			        Long obj2 = clineRepo.getNumberOfPurchases(b2.getId());
			        return obj1.compareTo(obj2);
				}).collect(Collectors.toList());
	}

	@Override
	public List<Book> getCheapestBooks() {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.sorted((b1,b2) -> {
					Float f1 = new Float(b1.getPrix());
					return f1.compareTo(new Float(b2.getPrix())); 
				})
				.limit(5).collect(Collectors.toList());
	}

	@Override
	public List<Book> getMostWantedBooks() {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.sorted((b1,b2)-> b2.getWishlists().size() - b1.getWishlists().size())
				.limit(20).collect(Collectors.toList());
	}
	
	@Override
	public List<Book> getMostInteractiveBooks(){
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.sorted((b1,b2)-> b2.getInteractions().size() - b1.getInteractions().size())
				.limit(20).collect(Collectors.toList());
	}
	
	public List<Book> getMostLovedBooks() {
		List<Book> books = (List<Book>) bookRepo.findAll();
		return books.stream()
				.sorted((b1,b2)-> interRepo.getNumberOfLikes(b2.getId()) - interRepo.getNumberOfLikes(b1.getId()))
				.limit(20).collect(Collectors.toList());
	}

}
