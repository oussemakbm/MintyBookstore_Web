package com.project.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.entities.Interaction;

public interface InteractionRepo extends CrudRepository<Interaction, Long> {
		
	@Query("Update i set i.liked = 1 WHERE i.id = commentId ")
	public void likeBook(long BookId); 
	
	@Query("Update i set i.liked = 0 WHERE i.id = commentId ")
	public void unlikeBook(long BookId);
	
	
	
}
