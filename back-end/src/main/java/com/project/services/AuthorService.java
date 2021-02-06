package com.project.services;
import com.project.entities.Author; 

import java.util.List;
public interface AuthorService {

	public Long addAuthor(Author author);
	
	public void deleteAuthor(Author author);
	
	public void deleteById(Long id);
	
	public Author findAuthorById(Long id);
	
	public List<Author> getAuthors();

}
