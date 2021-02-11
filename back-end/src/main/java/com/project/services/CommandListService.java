package com.project.services;

import java.util.List;



import com.project.DTOs.CommandLineDTO;
import com.project.DTOs.CommandListDTO;
import com.project.entities.CommandList;
import com.project.entities.Status;

public interface CommandListService {

	public CommandList updateCommandList(long idCommandList, Status newStatus);
	
	public void clearCommandList (long idCommandList);
	
	public List<CommandList> getCommandListsByIdUser();
	
	public Long addCommandList(CommandLineDTO clDTO);
	
	public List<CommandListDTO> getCommandLists(String search);
	

	
}
