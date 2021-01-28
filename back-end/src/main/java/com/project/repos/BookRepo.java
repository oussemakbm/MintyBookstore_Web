package com.project.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{

	public List<Book> findByTitleIgnoreCaseContaining();
}
