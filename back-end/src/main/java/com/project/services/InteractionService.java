package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.entities.Interaction;
import com.project.repos.InteractionRepo;

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
	
//	public void likeBook(Interaction i) {
//		interactionRepo.likeBook(i);
//	}
//	
//	public void unlikeBook(Interaction i) {
//		interactionRepo.unlikeBook(i);
//	}
//	
//	public void updateRating(Interaction i) {
//		interactionRepo.updateRating(i);
//	}
	
	
	/** Retourner Nombre de J'aime par Book **/
	public int getNumberOfLikes(long idbook){
		return interactionRepo.getNumberOfLikes(idbook);
	}
	
	
}
