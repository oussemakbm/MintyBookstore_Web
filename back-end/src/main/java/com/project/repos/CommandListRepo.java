package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.DTOs.CommandListDTO;
import com.project.entities.CommandList;
import com.project.entities.Status;
import com.project.entities.User;

public interface CommandListRepo extends CrudRepository<CommandList, Long>{
	

	@Query("SELECT cl FROM CommandList cl  WHERE cl.user.id= :idUser")
	public List<CommandList> getCommandListsByIdUser(@Param("idUser")long idUser);

	@Query("Select cl FROM CommandList cl WHERE cl.status= :status ")
	public List<CommandList> getCommandListsByStatus(@Param ("status") Status status);
	
	@Query("Select cl FROM CommandList cl WHERE cl.user.id= :idUser and cl.status= :status")
	public CommandList getCommandList(@Param("idUser")long idUser, @Param ("status") Status status);
}
