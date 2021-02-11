package com.project.services;

import java.util.List;

import com.project.entities.Book;
import com.project.entities.Category;
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
}
