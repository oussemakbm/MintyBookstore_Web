package com.project.services;

import java.util.List;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;

public interface CommandListService {

	public CommandList updateCommandList(long idCommandList, String newStatus);
	
	public void clearCommandList (long idCommandList);
	
	public List<CommandList> getAllCommandListsByUser(long id);
	
	public void deleteCommandList(CommandList commandList);
	
	public void deleteCommandListById(Long idCommandList);
	
	public Long addCommandList(CommandList cml);
	

	
}
