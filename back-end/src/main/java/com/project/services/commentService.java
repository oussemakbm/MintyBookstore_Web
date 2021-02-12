package com.project.services;

import java.io.IOException;
import java.util.List;

import com.project.entities.Comment;

import net.minidev.json.parser.ParseException;

public interface commentService {
	
	public boolean addComment(String body, long bookId) throws UnsupportedOperationException, IOException, ParseException;
	public boolean removeComment(long bookId);
	public List<Comment> getBookComments(long bookId);
	public boolean updateComment(long commentId, String body);
	
}
