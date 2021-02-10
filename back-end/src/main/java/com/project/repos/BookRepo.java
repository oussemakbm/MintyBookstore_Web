package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{

	/***   Book (Titre, Category, Author, Langue, Serie)   ***/
	@Query("SELECT b FROM Book b WHERE b.title= :name")
	public List<Book> getBooksByTitre(@Param("name")String name);
	
	@Query("SELECT count(*) FROM Book b WHERE b.title= :name")
	public int findBooksByTitre(@Param("name")String name);
	
	@Query("SELECT b FROM Book b WHERE b.language.id = :id")
	public List<Book> getBooksByLangue(@Param("id")long id);
	
	@Query("SELECT b FROM Book b WHERE b.category.id= :id")
	public List<Book> getBooksByCategory(@Param("id")long id);
	
	@Query("SELECT b FROM Book b WHERE b.author.id = :id")
	public List<Book> getBooksByAuthor(@Param("id")long id);
	
	@Query("SELECT b FROM Book b WHERE b.serie.id= :id")
	public List<Book> getBooksBySerie(@Param("id")long id);

	/***  Prix  ***/
	public List<Book> findByPrixLessThanEqual(float p);
	public List<Book> findByPrixBetween(float p1, float p2);
	public List<Book> findByPrixGreaterThan(float p);

	/***  Quantity  ***/
	@Query("SELECT b FROM Book b WHERE b.quantity= 0")
	public List<Book> findNotAvailable();
	public List<Book> findByQuantityLessThanEqual(long p);
	public List<Book> findByQuantityBetween(long q1, long q2);
	public List<Book> findByQuantityGreaterThan(long p);
	
	@Modifying
	@Query("update Book b set b.quantity = b.quantity + :qty where b.id = :id")
	public int addQuantity(@Param("id") long id, @Param("qty") long qty);
	
	/***  Rating  ***/
	public List<Book> findByRatingLessThanEqual(long p);
	public List<Book> findByRatingBetween(long q1, long q2);
	public List<Book> findByRatingGreaterThan(long p);
	public List<Book> findByRatingEquals(long p);
	
	/***  BestBooks (Rating) - BestBooks (Sellers) - BestBooks (Cheapest) - Most Wishted Books  ***/
	//@Query("SELECT b FROM Book b WHERE SUBSTRING(b.publishDate, 7, 4) = :year orber by b.rating desc LIMIT 20")
	//public List<Book> getBestBooksByYear(@Param("year") String year);
	
	//@Query("SELECT b FROM Book b WHERE SUBSTRING(b.publishDate, 4, 2) = :month orber by b.rating desc LIMIT 20")
	//public List<Book> getBestBooksByMonth(@Param("month") String month);
	
	public List<Book> findTop20ByOrderByRatingDesc();
	
	//@Query("SELECT b FROM Book b orber by b.prix LIMIT 20")
	//public List<Book> getCheapestBooks();

}
