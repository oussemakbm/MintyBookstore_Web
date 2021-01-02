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
	public Long addBook(Book book) {
		//obj = bookRepo.save(book);
		//bookRepo.flush();
		//obj.getId(); // get id here
		if(book == null)
			return null;
		return bookRepo.save(book).getId();
	}

	@Override
	public void deleteBook(Book book) {
		if(book != null)
			bookRepo.delete(book);
	}

	@Override
	public void deleteById(Long id) {
		if(id != null)
			bookRepo.deleteById(id);
	}
	
	@Override
	public Book findBookById(Long id) {
		if(id == null)
			return null;
		return bookRepo.findById(id).get();
	}

	@Override
	public List<Book> getBooks() {
		return (List<Book>) bookRepo.findAll();
	}

	@Override
	public Long updateBook(Book book) {
		if(book == null)
			return null;
		return bookRepo.save(book).getId();
	}
	

}
