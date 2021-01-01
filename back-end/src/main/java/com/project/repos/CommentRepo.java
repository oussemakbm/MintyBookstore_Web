package com.project.repos;

import org.springframework.data.repository.CrudRepository;

import com.project.entities.Comment;

public interface CommentRepo extends CrudRepository<Comment, Long> {
	
}
