package com.project.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entities.Interaction;

public interface InteractionRepo extends CrudRepository<Interaction, Long> {

	@Query("UPDATE Interaction SET i.liked = 1  WHERE i.id = :Inter.id ")
	public void likeBook(@Param("Inter") Interaction i); 
	
	@Query("UPDATE Interaction SET i.liked = 0  WHERE i.id = :Inter.id ")
	public void unlikeBook(@Param("Inter") Interaction i);
	
	@Query("UPDATE Interaction SET i.rating_value = i.ratingValue WHERE i.id = :Inter.id")
	public void updateRating(@Param("Inter") Interaction i);
	
}
