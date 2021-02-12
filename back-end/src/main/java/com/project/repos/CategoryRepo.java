package com.project.repos;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.DTOs.NumCategoryDTO;
import com.project.entities.Category;




public interface CategoryRepo extends CrudRepository<Category, Long>{
	
	@Query("Select c FROM Category c")
	public List<Category> getCategories();
	
	
	@Query("Select c FROM Category c ORDER BY c.name")
	List<Category> getCategoriesByNameAsc();
	
	@Query("Select c FROM Category c ORDER BY c.name DESC")
	List<Category> getCategoriesByNameDesc();
	
	/*@Query("select c.name,count(*) from Book b   join Category c  on b.category.id=c.id group by c.name  ")
	List<NumCategoryDTO> numberOfBooksByCategory();*/
	

	//@Query(value="select categories.book_id, books.name from categories join books on books.id = categories.book_id group by ",nativeQueru= true)
}
