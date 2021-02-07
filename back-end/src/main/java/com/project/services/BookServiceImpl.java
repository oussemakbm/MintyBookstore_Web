package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public Book addOrUpdateBook(BookDetailDTO bookdto) {
		Book book =bookConverter.DetailDTOToentity(bookdto);
		if(!(authorRepo.existsById(bookdto.getAuthor().getId()) && categoryRepo.existsById(bookdto.getCategory().getId()) &&
			serieRepo.existsById(bookdto.getSerie().getId()) && langueRepo.existsById(bookdto.getLanguage().getId())))
			return null;
		Author a = authorRepo.findById(bookdto.getAuthor().getId()).get();
		book.setAuthor(a);
		Category c = categoryRepo.findById(bookdto.getCategory().getId()).get();
		book.setCategory(c);
		Serie s = serieRepo.findById(bookdto.getSerie().getId()).get();
		book.setSerie(s);
		Langue l = langueRepo.findById(bookdto.getLanguage().getId()).get();
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
	
	public List<Book> getBookByTitre(String name){
		return bookRepo.getBookByTitre(name);
	}
	
	public boolean findBookByTitre(String name){
		if( 0 == bookRepo.findBookByTitre(name))
			return false;
		return true;
	}

}
