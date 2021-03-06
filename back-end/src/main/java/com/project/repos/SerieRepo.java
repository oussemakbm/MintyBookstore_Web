package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;
import com.project.entities.Serie;

@Repository
public interface SerieRepo extends CrudRepository<Serie, Long>{
	
	/*@Query("SELECT s FROM Serie s WHERE s.name= :name")
	public List<Book> findSerieByTitre(@Param("name")String name);*/
}
