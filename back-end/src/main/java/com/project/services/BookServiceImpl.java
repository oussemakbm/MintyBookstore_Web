package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.repos.BookRepo;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepo bookRepo;
	
	@Override
	public long addOrUpdateBook(Book book) {
		bookRepo.save(book);
		return book.getId();
	}

	@Override
	public void deleteBook(long id) {
		bookRepo.deleteById(id);
	}
	
	@Override
	public Book findBookById(long id) {
		return bookRepo.findById(id).get();
	}

	@Override
	public List<Book> getBooks() {
		return (List<Book>) bookRepo.findAll();
	}
	
	public List<Book> getBookByTitre(String name){
		return bookRepo.findBookByTitre(name);
	}

}
