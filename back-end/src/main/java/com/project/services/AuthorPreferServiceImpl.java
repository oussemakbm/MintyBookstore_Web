package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Author;
import com.project.entities.User;
import com.project.repos.AuthorRepo;
import com.project.repos.UserRepo;

@Service
public class AuthorPreferServiceImpl implements AuthorPreferService{

	@Autowired
	UserRepo userRepo;
    @Autowired
    AuthorRepo authorRepo ;
	@Override
	public void addAuthorPrefer( Long user_id, Long author_id) {
		Author author = authorRepo.findById(author_id).get();
		User user = userRepo.findById(user_id).get();
		user.getFavoriteAuthors().add(author);
		
	}

	@Override
	public void deleteAuthorPrefer(Long user_id, Long author_id) {
		Author author = authorRepo.findById(author_id).get();
		User user = userRepo.findById(author_id).get();
		user.getFavoriteAuthors().remove(author);	
		
	}

	@Override
	public List<Author> getAllPreferAuthor(Long user_id) {
		User user = userRepo.findById(user_id).get();
		return user.getFavoriteAuthors();
	}

	@Override
	public Author getPreferAuthor(Long user_id, Long author_id) {
		Author author = authorRepo.findById(author_id).get();
		User user = userRepo.findById(user_id).get();
		return authorRepo.findById(author_id).get();
	}
}
