package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.Interaction;
import com.project.entities.User;
import com.project.repos.BookRepo;
import com.project.repos.InteractionRepo;
import com.project.repos.UserRepo;

@Service
public class InteractionService  {
		
	/*
	 * 
	 * About adding interaction 
	 * 	- if the interaction doesn't exist the Database 
	 * 		Then we must create a new interaction and save it ( to the database ofcours )
	 * 	- else if the interaction exists
	 * 		- Then all we do is update the interaction value (in the database ofcours )
	 * 
	 * 
	 * 
	 * */
	
	@Autowired
	InteractionRepo interactionRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	BookRepo bookRepo;
	
		
		
	public List<Interaction> getBookInteractions(long bookId){
		List<Interaction> result = interactionRepo.getBookInteractions(bookId);
		return result;
	}
	
	public Interaction AddOrUpdateInteraction(long userId, long bookId, boolean liked, double ratingValue) {
		Interaction userInteraction = interactionRepo.findByUserInteraction(bookId, userId);
		
		if (userInteraction != null) {
			userInteraction.setLiked(liked);
			userInteraction.setRatingValue(ratingValue);
			return interactionRepo.save(userInteraction);
		}
		
		User u = userRepo.findById(userId).get();
		Book b = bookRepo.findById(bookId).get();
		
		userInteraction = new Interaction(u, b, ratingValue, liked);
		
		return interactionRepo.save(userInteraction);
		
	}
	
	public double averageRating(long bookId) {
		List<Interaction> interactions = interactionRepo.getBookInteractions(bookId);
		double averageRating = interactions.stream().mapToDouble(i -> i.getRatingValue()).average().getAsDouble();
		return averageRating;
	}
	
	public double nbrLikes(long bookId) {
		List<Interaction> interactions = interactionRepo.getBookInteractions(bookId);
		double bookLikes = interactions.stream().filter(i -> i.isLiked() == true).count();
		return bookLikes;
	}
	
	
	
}
