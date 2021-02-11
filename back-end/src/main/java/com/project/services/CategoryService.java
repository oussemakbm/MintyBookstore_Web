package com.project.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.project.DTOs.CategoryDTO;
import com.project.entities.Book;
import com.project.entities.Category;
import com.project.entities.User;
import com.project.entities.Wishlist;



public interface CategoryService {

	
    public void deleteCategory(Category category);
	
	//public void deleteById(Long id);
	
	public Category findCategoryById(long id);
	
	public List<Category> getCategories();
	
	public Category addCategory(String categoryName);
	
	public Category clearCategory(long idCategory);
	
	
	public List<Book> getAllBooksInCategory(long idCategory);
	
	public Category removeBookFromCategory(long idCategory, long idBook );
	
	public boolean updateCategory (Category c);
	
     public List<CategoryDTO> getCategoriesByNameAsc();
	
	
	public List<CategoryDTO> getCategoriesByNameDesc();
}
