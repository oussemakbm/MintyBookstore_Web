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
	
	
	/*------------- User Management by admin --------*/
	
	@Query("Select u FROM User u WHERE (UPPER(u.username) like UPPER(:search)) OR (UPPER(u.email) like UPPER(:search))"
			+ " OR (u.numTel like :search) OR (UPPER(u.adresse) like UPPER(:search)) "
			+ "OR (UPPER(u.name) like UPPER(:search))")
	List<User> getUsers(String search);
	
	
	
	@Query("Select u FROM User u ORDER BY u.username")
	List<User> getUsersByUsernameAsc();
	
	@Query("Select u FROM User u ORDER BY u.username DESC")
	List<User> getUsersByUsernameDesc();
	
	
	@Query("Select u FROM User u ORDER BY u.email")
	List<User> getUsersByEmailAsc();
	
	@Query("Select u FROM User u ORDER BY u.email DESC")
	List<User> getUsersByEmailDesc();
	
	
	@Query("Select u FROM User u ORDER BY u.name")
	List<User> getUsersByNameAsc();
	
	@Query("Select u FROM User u ORDER BY u.name DESC")
	List<User> getUsersByNameDesc();
	
	
	@Query("Select u FROM User u ORDER BY u.role")
	List<User> getUsersByRoleAsc();
	
	@Query("Select u FROM User u ORDER BY u.role DESC")
	List<User> getUsersByRoleDesc();
	
	
	
}
