package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Interaction;
@Repository
public interface InteractionRepo extends CrudRepository<Interaction, Long> {

//	@Query("UPDATE Interaction SET i.liked = 1  WHERE i.id = :Inter.id ")
//	public void likeBook(@Param("Inter") Interaction i); 
//	
//	@Query("UPDATE Interaction SET i.liked = 0  WHERE i.id = :Inter.id ")
//	public void unlikeBook(@Param("Inter") Interaction i);
//	
//	@Query("UPDATE Interaction SET i.rating_value = i.ratingValue WHERE i.id = :Inter.id")
//	public void updateRating(@Param("Inter") Interaction i);
	
	/** Retourner Nombre de J'aime par Book **/
	@Query("Select count(i) from Interaction i WHERE i.book.id = :idbook and i.liked = true")
	public int getNumberOfLikes(@Param("idbook") long idbook);
	
	@Query("SELECT i FROM Interaction i WHERE i.book.id = :bookId")
	public List<Interaction> getBookInteractions(@Param("bookId") long bookId);
	
	
	@Query("SELECT i FROM Interaction i WHERE i.user.id = :userId AND i.book.id = :bookId")
	public Interaction findByUserInteraction(@Param("bookId") long bookId, @Param("userId") long userId);
	
}
