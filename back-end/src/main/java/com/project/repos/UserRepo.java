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

	User findByUsername(String username);
	
	@Query("Select c FROM CommandList c WHERE c.user.id = :userId")
	List<CommandList> getUserCommandList(long userId);
	
}
