package com.project.converter;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.AuthorDTO;
import com.project.DTOs.SerieDTO;
import com.project.entities.Author;
import com.project.entities.Serie;



@Component
public class AuthorConverter {

	ModelMapper modelMapper = new ModelMapper();

	public AuthorDTO entityToDTO(Author author){
		AuthorDTO authordto = new AuthorDTO();
		authordto = modelMapper.map(author, AuthorDTO.class);
		return authordto;
	}
	public List<AuthorDTO> entitiesToDTOs(List<Author> authors){
		return authors.stream().map(a -> entityToDTO(a)).collect(Collectors.toList());
	}
	public Author DTOToentity(AuthorDTO authordto){
		Author author = new Author();
		author = modelMapper.map(authordto, Author.class);
		return author;
	}
}
