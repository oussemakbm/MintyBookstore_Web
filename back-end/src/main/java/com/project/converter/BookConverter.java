package com.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.BookDTO;
import com.project.DTOs.BookDetailDTO;
import com.project.entities.Book;

@Component
public class BookConverter {
	
	ModelMapper modelMapper = new ModelMapper();

	public BookDetailDTO entityToDetailDTO(Book book){
		BookDetailDTO bookd = new BookDetailDTO();
		bookd = modelMapper.map(book, BookDetailDTO.class);
		bookd.setAuthor(book.getAuthor().getName());
		bookd.setSerie(book.getSerie().getName());
		bookd.setLanguage(book.getLanguage().getName());
		bookd.setCategory(book.getCategory().getName());
		return bookd;
	}
	
	public BookDTO entityToDTO(Book book){
		BookDTO bookd = new BookDTO();
		return modelMapper.map(book, BookDTO.class);
	}
	
}
