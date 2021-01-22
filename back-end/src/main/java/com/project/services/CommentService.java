package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Comment;
import com.project.repos.CommentRepo;


@Service
public class CommentService {
	
	/*@Autowired
	CommentRepo commentRepo;
	
	
	public void addComment(Comment commentToAdd) {
		commentRepo.save(commentToAdd);
	}
	
	public void removeComment(Comment commentToRemove) {
		commentRepo.delete(commentToRemove);
	}
	
//	public List<Comment> getBookComments(long bookId) {
//		return commentRepo.getCommentsByBookId(bookId);
//	}
	
	public Comment updateComment(Comment newComment) {
		return commentRepo.save(newComment);
	}
	*/
}
