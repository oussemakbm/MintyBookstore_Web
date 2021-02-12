package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<SerieDTO> entitiesToDTOs(List<Serie> series){
		return series.stream().map(s -> entityToDTO(s)).collect(Collectors.toList());
	}
	
	public Serie DTOToentity(SerieDTO seriedto){
		Serie serie = new Serie();
		serie = modelMapper.map(seriedto, Serie.class);
		return serie;
	}

}
