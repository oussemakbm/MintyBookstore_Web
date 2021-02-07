package com.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.AuthorDTO;
import com.project.entities.Author;



@Component
public class AuthorConverter {

	ModelMapper modelMapper = new ModelMapper();

	public AuthorDTO entityToDTO(Author author){
		AuthorDTO authordto = new AuthorDTO();
		authordto = modelMapper.map(author, AuthorDTO.class);
		return authordto;
	}
	
	public Author DTOToentity(AuthorDTO authordto){
		Author author = new Author();
		author = modelMapper.map(authordto, Author.class);
		return author;
	}
}
