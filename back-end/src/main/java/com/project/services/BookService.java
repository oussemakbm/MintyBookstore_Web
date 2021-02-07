package com.project.services;

import java.util.List;

import com.project.DTOs.BookDetailDTO;
import com.project.entities.Book;

public interface BookService {
	
	public Book addOrUpdateBook(BookDetailDTO book);
		
	public boolean deleteBook(long id);
	
	public Book findBookById(long id);
	
	public List<Book> getBooks();
	
	public List<Book> getBookByTitre(String name);
}
