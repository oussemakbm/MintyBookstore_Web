package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entities.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {
	
	@Query("SELECT c FROM Comment WHERE book.id = :bookId")
	List<Comment> getCommentsByBookId(@Param("bookId") long bookId);
	
	
	
}