package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entities.Comment;
import com.project.entities.Langue;

public interface LanguageRepo extends CrudRepository<Langue, Long> {
	
	@Query("SELECT l FROM Langue l WHERE l.id = :langueId")
	List<Langue> findLangueById(@Param("langueId") long bookId);

}
