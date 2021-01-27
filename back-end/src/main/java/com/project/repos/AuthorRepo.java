package com.project.repos;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.project.entities.Author;

@Repository 
public interface AuthorRepo extends CrudRepository<Author, Long> {

}
