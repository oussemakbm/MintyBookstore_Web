package com.project.services;
  
import java.util.List;

import com.project.entities.Author;


public interface AuthorPreferService {
public boolean addAuthorPrefer( long user_id,long author_id);
public boolean deleteAuthorPrefer(long user_id,long serie_id);
public List<Author> getAllPreferAuthor(long user_id);
public List<Author> findPreferAuthorByName (long user_id, String name);
}
