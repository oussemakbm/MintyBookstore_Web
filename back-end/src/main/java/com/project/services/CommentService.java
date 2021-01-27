package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.Comment;
import com.project.entities.User;
import com.project.repos.BookRepo;
import com.project.repos.CommentRepo;
import com.project.security.UserUtilities;


@Service
public class CommentService {
	
	@Autowired
	CommentRepo commentRepo;
	@Autowired
	BookRepo bookRepo;
	@Autowired
	UserUtilities userUtil;
	
	
	public boolean addComment(String body, long bookId) {
		Book b = bookRepo.findById(bookId).get();
		User u = userUtil.getCurrentAuthenticatedUser();
		Comment c = new Comment(body,b,u);
		Long savedCommentId = commentRepo.save(c).getId();
		return commentRepo.findById(savedCommentId).isPresent();
	}
	
	public boolean removeComment(long bookId) {
		commentRepo.deleteById(bookId);
		return !commentRepo.findById(bookId).isPresent();
	}
	
	public List<Comment> getBookComments(long bookId) {
		return commentRepo.getCommentsByBookId(bookId);
	}
	
	public boolean updateComment(long commentId, String body) {
		Comment newComment = commentRepo.findById(commentId).get();
		newComment.setBody(body);
		Long savedCommentId = commentRepo.save(newComment).getId();
		return commentRepo.findById(commentId).get().getBody().equals(body);
	}
	
}
