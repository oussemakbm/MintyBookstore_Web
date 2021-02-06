package com.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.SerieDTO;
import com.project.entities.Serie;

@Component
public class SerieConverter {
	
	ModelMapper modelMapper = new ModelMapper();

	public SerieDTO entityToDTO(Serie serie){
		SerieDTO seriedto = new SerieDTO();
		seriedto = modelMapper.map(serie, SerieDTO.class);
		return seriedto;
	}
	
	public Serie DTOToentity(SerieDTO seriedto){
		Serie serie = new Serie();
		serie = modelMapper.map(seriedto, Serie.class);
		return serie;
	}

}
