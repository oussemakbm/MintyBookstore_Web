package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.InteractionDTO;
import com.project.entities.Interaction;

@Component
public class InteractionConverter {
	
ModelMapper modelMapper = new ModelMapper();
	
	
	public InteractionDTO entityToDTO(Interaction i){		
		InteractionDTO b = modelMapper.map(i, InteractionDTO.class);
		return b;
	}
	
	
	public List<InteractionDTO> entitiesToDTOs(List<Interaction> interactions){
		return interactions.stream().map(b -> entityToDTO(b)).collect(Collectors.toList());
	}
	
	
}
