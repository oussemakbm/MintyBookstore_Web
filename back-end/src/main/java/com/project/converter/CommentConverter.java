package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.project.DTOs.CommentReturnDTO;
import com.project.entities.Comment;

@Component
public class CommentConverter {
	ModelMapper modelMapper = new ModelMapper();
	
	
	public CommentReturnDTO entityToDTO(Comment comment){		
		CommentReturnDTO b = modelMapper.map(comment, CommentReturnDTO.class);
		return b;
	}
	
	
	public List<CommentReturnDTO> entitiesToDTOs(List<Comment> comments){
		return comments.stream().map(b -> entityToDTO(b)).collect(Collectors.toList());
	}
	
}
