package com.project.services;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.*;

import java.util.List;


public interface CommandLineService {

	public List<CommandLine> findByCommandList(long idCommandList);
	
	
	public boolean addCommandLine(CommandLineDTO clDTO);
	
	public CommandLine updateCommandLine(CommandLineDTO clDTO);
	
	public boolean deleteCommandLine (long idCommandLine, long idCommandList);
	
	public Book getBookInCommadnLine(long id);

	

	public List<String> gettopfiveofbooks();
	
	
	
	
}
