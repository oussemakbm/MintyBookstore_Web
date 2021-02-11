package com.project.services;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.*;

import java.util.List;


public interface CommandLineService {

	public List<CommandLine> findByCommandList(long idCommandList);
	
	//public CommandLine findByid(long id);
	
	//public CommandLine addBookToCommandLine (CommandLineDTO commandLine);
	
	public boolean addCommandLine(CommandLineDTO clDTO);
	
	public CommandLine updateCommandLine(CommandLineDTO clDTO);
	
	public boolean deleteCommandLine (long idCommandLine, long idCommandList);
	
	//public CommandLine save(CommandLine commandLine);
	
	//public CommandLine deleteBookFromCommandLine(long idBook, long idCommandLine);
	
	//public Book getBookInCommadnLine(long id);
	
	
}
