package com.project.services;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.*;

import java.util.List;


public interface CommandLineService {

	public List<CommandLine> findByCommandList(long idCommandList);
	
	public String addCommandLine(long idbook);
	
	public CommandList updateCommandLine(long idCommandLine, long q);
	
	public boolean deleteCommandLine (long idCommandLine);
	
	public Book getBookInCommadnLine(long id);

	public List<String> gettopfiveofbooks();
		
	
}
