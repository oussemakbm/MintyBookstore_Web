package com.project.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.UserDTO;
import com.project.entities.User;


@Component
public class UserConverter {
	ModelMapper modelMapper = new ModelMapper();
	
	public UserDTO entityToDTO(User user){		
		UserDTO u = modelMapper.map(user, UserDTO.class);
		return u;
	}
	
	
	public List<UserDTO> entitiesToDTOs(List<User> users){
		List<UserDTO> list=new ArrayList<UserDTO>();
		for (User u : users) {
			list.add(entityToDTO(u));
		}
		return list;
	}
	
}
