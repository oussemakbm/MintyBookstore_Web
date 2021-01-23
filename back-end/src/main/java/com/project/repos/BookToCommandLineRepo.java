package com.project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.BookToCommandLine;
import com.project.entities.CommandLine;

@Repository
public interface BookToCommandLineRepo extends CrudRepository<BookToCommandLine, Long>{
	
	public void deletByCommandLine(CommandLine commandLine);
	
	
	

}
