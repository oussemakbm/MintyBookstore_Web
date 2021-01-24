package com.project.services;

import java.util.List;

import com.project.entities.Category;

public interface CategoryService {

	
    public void deleteCategory(Category category);
	
	public void deleteById(Long id);
	
	public Category findCategoryById(long id);
	
	public List<Category> getCategories();
	
	public Long addOrUpdateCategory(Category category);
}
