package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.CategoryDTO;
import com.project.DTOs.CommandListDTO;
import com.project.entities.Category;
import com.project.entities.CommandList;

@Component
public class CommandListConverter {
	

	
		ModelMapper modelMapper = new ModelMapper();
		public CommandListDTO entityToDTO(CommandList cl){
			CommandListDTO clDTO = new CommandListDTO();
			clDTO = modelMapper.map(cl, CommandListDTO.class);
			clDTO.setUserId(cl.getUser().getId());
		return clDTO;
}

    	public CommandList DTOToentity(CommandListDTO clDTO){
    		CommandList cl = new CommandList();
    		cl = modelMapper.map(clDTO, CommandList.class);
		return cl;
}
    	public List<CommandListDTO> entitiesToCommandListsDTOs(List<CommandList> commandLists){
    		return commandLists.stream().map(s -> entityToDTO(s)).collect(Collectors.toList());
    	}
}
