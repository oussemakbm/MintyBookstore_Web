package com.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.entities.Category;
import com.project.entities.User;



public interface CategoryRepo extends CrudRepository<Category, Long>{
	
	@Query("Select c FROM Category c")
	public List<Category> getCategories();
	
	
	@Query("Select c FROM Category c ORDER BY c.name")
	List<Category> getCategoriesByNameAsc();
	
	@Query("Select c FROM Category c ORDER BY c.name DESC")
	List<Category> getCategoriesByNameDesc();

}
