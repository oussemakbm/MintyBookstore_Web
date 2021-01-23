package com.project.services;

import com.project.entities.*;

import java.util.List;


public interface CommandLineService {

	public List<CommandLine> findByCommandList(CommandList commandList);
	
	public CommandLine findByid(long id);
	
	public CommandLine addBookToCommandLine (Book book, User user, int quantity);
	
	public CommandLine updateCommandLine(CommandLine commandLine);
	
	public void removeCommandLine (CommandLine commandLine);
	
	public CommandLine save(CommandLine commandLine);
	
	
	
}
