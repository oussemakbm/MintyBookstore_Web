package com.project.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;


@Repository
public interface CommandLineRepo extends CrudRepository<CommandLine, Long>{
	
//	Commenting this so i can run the project
//	List<CommandLine> findByCommandList(CommandList commandList);
	

}
