package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.entities.Category;



public interface CategoryRepo extends CrudRepository<Category, Long>{
	
	@Query("Select c FROM Category c")
	public List<Category> getCategories();

}
