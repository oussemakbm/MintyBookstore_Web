package com.project.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.BookDTO;
import com.project.DTOs.BookDTOA;
import com.project.DTOs.BookDetailDTO;
import com.project.DTOs.SerieDTO;
import com.project.DTOs.AuthorDTO;
import com.project.DTOs.CategoryDTO;
import com.project.DTOs.LangueDTO;
import com.project.entities.Book;
import com.project.entities.Author;
import com.project.entities.Langue;
import com.project.entities.Serie;
import com.project.entities.Category;

@Component
public class BookConverter {
	
	ModelMapper modelMapper = new ModelMapper();

	/*** Book Detail DTO ***/
	public BookDetailDTO entityToDetailDTO(Book book){
		BookDetailDTO bookd = new BookDetailDTO();
		bookd = modelMapper.map(book, BookDetailDTO.class);
		bookd.setAuthor(modelMapper.map(book.getAuthor(), AuthorDTO.class));
		bookd.setSerie(modelMapper.map(book.getSerie(), SerieDTO.class));
		bookd.setLanguage(modelMapper.map(book.getLanguage(), LangueDTO.class));
		bookd.setCategory(modelMapper.map(book.getCategory(), CategoryDTO.class));
		bookd.setAvailable();
		return bookd;
	}
	
	public Book DetailDTOToentity(BookDetailDTO bookdto){
		Book book = new Book();
		book = modelMapper.map(bookdto, Book.class);
		return book;
	}
	
	public List<BookDetailDTO> entitiesToDetailDTOs(List<Book> books){
		return books.stream().map(b -> entityToDetailDTO(b)).collect(Collectors.toList());
	}
	
	/*** Book DTO ADMIN ***/
	public Book DTOAToentity(BookDTOA bookdtoa){
		Book book = new Book();
		book = modelMapper.map(bookdtoa, Book.class);
		return book;
	}
	
	public BookDTOA entitytToDTOA(Book book){
		BookDTOA bookdtoa = new BookDTOA();
		bookdtoa = modelMapper.map(book, BookDTOA.class);
		bookdtoa.setAuthorID(book.getAuthor().getId());
		if(book.getSerie() == null) {
			//Long serie = (Long) null;
			//bookdtoa.setSerieID(serie);
		} else
			bookdtoa.setSerieID(book.getSerie().getId());
		bookdtoa.setLanguageID(book.getLanguage().getId());
		bookdtoa.setCategoryID(book.getCategory().getId());
		bookdtoa.setAvailable();
		return bookdtoa;
	}
	
	/*** Book DTO ***/
	public BookDTO entityToDTO(Book book){		
		BookDTO b = modelMapper.map(book, BookDTO.class);
		b.setAvailable();
		return b;
	}
	
	public List<BookDTO> entitiesToDTOs(List<Book> books){
		return books.stream().map(b -> entityToDTO(b)).collect(Collectors.toList());
	}
	
}
