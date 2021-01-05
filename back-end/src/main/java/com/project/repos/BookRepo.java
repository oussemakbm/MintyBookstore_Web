package com.project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{

}
