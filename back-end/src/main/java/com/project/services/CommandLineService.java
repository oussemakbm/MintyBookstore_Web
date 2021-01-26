package com.project.services;

import com.project.entities.*;

import java.util.List;


public interface CommandLineService {

	public List<CommandLine> findByCommandList(long idCommandList);
	
	public CommandLine findByid(long id);
	
	public CommandLine addBookToCommandLine (Book book, User user, int quantity);
	
	public CommandLine updateCommandLine(CommandLine commandLine);
	
	public void removeCommandLine (CommandLine commandLine);
	
	public CommandLine save(CommandLine commandLine);
	
	public CommandLine deleteBookFromCommandLine(long idBook, long idCommandLine);
	
	public Book getBookInCommadnLine(long id);
	
	
}
