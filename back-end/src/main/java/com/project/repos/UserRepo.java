package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.CommandList;
import com.project.entities.Serie;
import com.project.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

	
	/* Favorites Series */
//	@Modifying
//	@Query(value = "INSERT INTO users_series (user_id , serie_id) VALUES (:user, :serie)", nativeQuery = true)
//	public void addToFavoriteSerie(@Param("user")Long user_id, @Param("serie")Long serie_id);
	
//	@Modifying
//	@Query("DELETE FROM users_series us WHERE us.user_id = :user AND us.serie_id = :serie", nativeQuery = true)
//	public int deleteFromFavoriteSerie(@Param("user")Long user_id, @Param("serie")Long serie_id);


//	@Query("SELECT u FROM User u WHERE u.user_id = :user")
//	public List<Serie> getAllFavoriteSeries(@Param("user")Long user_id);
//	
//	@Query("SELECT u FROM User u WHERE u.user_id = :user")
//	public Serie getFavoriteSerie(Long id);
	
	User findByUsername(String username);
	
	@Query("Select c FROM CommandList c WHERE c.user.id = :userId")
	List<CommandList> getUserCommandList(long userId);
	
}
