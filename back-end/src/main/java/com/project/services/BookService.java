package com.project.services;

import java.util.List;

import com.project.entities.Book;

public interface BookService {
	
	public long addOrUpdateBook(Book book);
		
	public void deleteBook(long id);
	
	public Book findBookById(long id);
	
	public List<Book> getBooks();
	
	public List<Book> getBookByTitre(String name);
}
