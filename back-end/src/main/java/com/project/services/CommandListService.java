package com.project.services;

import java.util.List;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;

public interface CommandListService {

	public CommandList updateCommandList (CommandList commandList) ;
	
	public void clearCommandList (CommandList commandList);
	
	public List<CommandList> getAllCommandListsByUser(long id);
	
	
	
	//public List<CommandLine> getAllCommandLinesByCommandList (long id);
	
}
