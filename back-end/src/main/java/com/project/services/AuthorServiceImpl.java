package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Author;
import com.project.repos.AuthorRepo;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepo authorRepo;
	
	@Override
	public Author addAuthor(Author author) {
	List<Author> authors = (List<Author>) authorRepo.findAll();
	boolean test=false;
	
		for(Author a : authors)
		{
			if((a.getName().equals(author.getName()))&&(a.getId()==author.getId())) {
				test=true;
				
			}
			
		}
		if((test)) {
		return null ;}
		authorRepo.save(author);
		return author ;
	}

	@Override
	public void deleteAuthor(Author author) {
		authorRepo.delete(author);
		
	}

	@Override
	public void deleteById(Long id) {
		authorRepo.deleteById(id);
		
	}
	
	@Override
	public Author findAuthorById(Long id) {
		
		return authorRepo.findById(id).get();
	}
	
	@Override
	public List<Author> getAuthors() {
		return (List<Author>) authorRepo.findAll();
		
	}

}
