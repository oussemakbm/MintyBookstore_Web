package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Comment;
import com.project.repos.CommentRepo;


@Service
public class CommentService {
	
	@Autowired
	CommentRepo commentRepo;
	
	
	public void addComment(long bookId) {
		
	}
	
	public void removeComment(long commentId) {
		
	}
	
	public List<Comment> getBookComments(long bookId) {
		
		return null;
	}
	
	public Comment updateComment(Comment newComment) {
		
		return null;
	}
	
}
