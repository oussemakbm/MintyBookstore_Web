package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.CommandLine;



@Component
public class CommandLineConverter {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public CommandLineDTO entityToDTO(CommandLine  cl){
		CommandLineDTO clDTO = new CommandLineDTO();
		clDTO = modelMapper.map(cl, CommandLineDTO.class);
		return clDTO;
	}

	public CommandLine DTOToentity(CommandLineDTO clDTO){
		CommandLine cl = new CommandLine();
		cl = modelMapper.map(clDTO, CommandLine.class);
		return cl;
	}
	
	public List<CommandLineDTO> entityToDTOs(List<CommandLine>  cl){
		return cl.stream().map(c -> this.entityToDTO(c)).collect(Collectors.toList());
	}

}
