package com.project.services;

import java.util.List;

import com.project.entities.Book;

public interface BookService {
	
	public Long addOrUpdateBook(Book book);
	
	public void deleteBook(Book book);
	
	public void deleteById(Long id);
	
	public Book findBookById(Long id);
	
	public List<Book> getBooks();	
}
