package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{

	@Query("SELECT b FROM Book b WHERE b.title= :name")
	public List<Book> findBookByTitre(@Param("name")String name);

}
