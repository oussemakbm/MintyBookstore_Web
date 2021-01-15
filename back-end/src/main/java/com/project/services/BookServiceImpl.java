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
	public Long addOrUpdateBook(Book book) {
		bookRepo.save(book);
		//bookRepo.flush();
		//obj.getId(); // get id here
		return book.getId();
	}

	@Override
	public void deleteBook(Book book) {
		bookRepo.delete(book);
	}

	@Override
	public void deleteById(Long id) {
		bookRepo.deleteById(id);
	}
	
	@Override
	public Book findBookById(Long id) {
		return bookRepo.findById(id).get();
	}

	@Override
	public List<Book> getBooks() {
		return (List<Book>) bookRepo.findAll();
	}

}
