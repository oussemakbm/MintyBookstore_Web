package com.project.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.CategoryDTO;

import com.project.entities.Category;



@Component
public class CategoryConverter {
	ModelMapper modelMapper = new ModelMapper();

	public CategoryDTO entityToDTO(Category category){
		CategoryDTO cDTO = new CategoryDTO();
		cDTO = modelMapper.map(category,  CategoryDTO.class);
		return cDTO;
	}
	
	public List<CategoryDTO> entitiesToCategoryDTOs(List<Category> categories){
		return categories.stream().map(s -> entityToDTO(s)).collect(Collectors.toList());
	}
	
	public Category DTOToentity(CategoryDTO cDTO){
		Category c =  new Category();
		c = modelMapper.map(cDTO, Category.class);
		return c;
	}
	
	

}
