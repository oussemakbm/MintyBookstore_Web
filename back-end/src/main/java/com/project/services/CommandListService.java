package com.project.services;

import com.project.entities.CommandList;

public interface CommandListService {

	public CommandList updateCommandList (CommandList commandList) ;
	
	public void clearCommandList (CommandList commandList);
	
}
