package com.project.services;

import java.util.List;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.Status;

public interface CommandListService {

	public CommandList updateCommandList(long idCommandList, Status newStatus);
	
	public void clearCommandList (long idCommandList);
	
	public List<CommandList> getAllCommandListsByUser(long id);
	
	public void deleteCommandList(CommandList commandList);
	
	public void deleteCommandListById(Long idCommandList);
	
	public Long addCommandList(CommandList cml);
	

	
}
