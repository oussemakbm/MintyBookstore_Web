package com.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Author;
import com.project.entities.User;
import com.project.repos.AuthorRepo;
import com.project.repos.UserRepo;

@Service
public class AuthorPreferServiceImpl implements AuthorPreferService {

	@Autowired
	UserRepo userRepo;
    @Autowired
    AuthorRepo authorRepo ;
	@Override
	public boolean addAuthorPrefer( long user_id, long author_id) {
		if(!(authorRepo.existsById(user_id) && authorRepo.existsById(author_id)))
			return false;
		Author author = authorRepo.findById(author_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteAuthors().add(author);
		return true;
	}

	@Override
	public boolean deleteAuthorPrefer(long user_id, long author_id) {
		if(!(userRepo.existsById(user_id)))
			return false;
		Author author = authorRepo.findById(author_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteAuthors().remove(author);	
		userRepo.save(user);
		return true;
	}

	@Override
	public List<Author> getAllPreferAuthor(long user_id) {
		if(!(userRepo.existsById(user_id)))
			return null;
		User user = userRepo.findById(user_id).get();
		return user.getFavoriteAuthors();
	}

	@Override
	public List<Author> findPreferAuthorByName (long user_id, String name) {
		if(!(userRepo.existsById(user_id)))
			return null;
		User user = userRepo.findById(user_id).get();
		List <Author> authors = user.getFavoriteAuthors().stream()
				.filter(a -> a.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
		return authors;
	}
	}

