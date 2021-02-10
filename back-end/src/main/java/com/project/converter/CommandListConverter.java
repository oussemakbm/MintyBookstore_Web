package com.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.CommandListDTO;
import com.project.entities.CommandList;

@Component
public class CommandListConverter {
	

	
		ModelMapper modelMapper = new ModelMapper();
		public CommandListDTO entityToDTO(CommandList cl){
			CommandListDTO clDTO = new CommandListDTO();
			clDTO = modelMapper.map(cl, CommandListDTO.class);
			//clDTO.setUser(cl.getUser().getId());
		return clDTO;
}

    	public CommandList DTOToentity(CommandListDTO clDTO){
    		CommandList cl = new CommandList();
    		cl = modelMapper.map(clDTO, CommandList.class);
		return cl;
}
	
}
