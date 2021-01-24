package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.Category;
import com.project.repos.CategoryRepo;

public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public void deleteCategory(Category category) {
		categoryRepo.delete(category);
	}

	@Override
	public void deleteById(Long id) {
		categoryRepo.deleteById(id);
		
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> getCategories() {
		return (List<Category>) categoryRepo.findAll();
	}

	@Override
	public Long addOrUpdateCategory(Category category) {
		categoryRepo.save(category);
		
		return category.getId();
	}
	

}
