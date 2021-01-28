package com.project.services;
  
import java.util.List;

import com.project.entities.Author;


public interface AuthorPreferService {
public void addAuthorPrefer( Long user_id,Long author_id);
public void deleteAuthorPrefer(Long user_id,Long serie_id);
public List<Author> getAllPreferAuthor(Long user_id);
public Author getPreferAuthor(Long user_id,Long author_id);
}
