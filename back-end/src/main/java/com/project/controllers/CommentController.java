package com.project.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.CommentDTO;
import com.project.DTOs.CommentReturnDTO;
import com.project.converter.CommentConverter;
import com.project.entities.Comment;
import com.project.services.CommentService;

import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	@Autowired
	CommentConverter commentConverter;
	
	
	@GetMapping("/{bookId}/all")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<CommentReturnDTO>> getBookComments(@PathVariable("bookId") long bookId) {
		List<Comment> result = commentService.getBookComments(bookId);
			if (result != null) {
				
				return new ResponseEntity<List<CommentReturnDTO>>(commentConverter.entitiesToDTOs(result), HttpStatus.ACCEPTED);
			}
		
			return new ResponseEntity<List<CommentReturnDTO>>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/{bookId}/add")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<Map<String, Boolean>> addComment(@PathVariable("bookId") long bookId, @RequestBody CommentDTO comment) throws UnsupportedOperationException, IOException, ParseException {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Added:", commentService.addComment(comment.getBody(), bookId));
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping
	(
			value="/{commentId}",
			method= RequestMethod.DELETE
	)
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> deleteComment(@PathVariable("commentId") long commentId) {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted:", commentService.removeComment(commentId));
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/{bookId}/{commentId}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<Map<String, Boolean>> updateComment(@PathVariable("bookId") long bookId, @PathVariable("commentId") long commentId,@RequestBody CommentDTO comment) {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Updated:", commentService.updateComment(commentId, comment.getBody()));
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.ACCEPTED);
	}
	
	
}