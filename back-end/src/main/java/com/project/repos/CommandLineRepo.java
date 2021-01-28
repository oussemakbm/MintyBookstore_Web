package com.project.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.CommandLine;
@Repository
public interface CommandLineRepo extends CrudRepository<CommandLine, Long>{
	
	List<CommandLine> findByCommandList(long idCommandList);
	

}
