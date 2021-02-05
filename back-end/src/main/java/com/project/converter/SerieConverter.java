package com.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.SerieDTO;
import com.project.entities.Serie;

@Component
public class SerieConverter {
	
	ModelMapper modelMapper = new ModelMapper();

	public SerieDTO entityToDTO(Serie serie){
		SerieDTO serieid = new SerieDTO();
		serieid = modelMapper.map(serie, SerieDTO.class);
		return serieid;
	}

}
