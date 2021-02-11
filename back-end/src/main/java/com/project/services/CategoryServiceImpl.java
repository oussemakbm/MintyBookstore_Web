package com.project.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTOs.CategoryDTO;
import com.project.converter.CategoryConverter;
import com.project.entities.Book;
import com.project.entities.Category;
import com.project.repos.BookRepo;
import com.project.repos.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CategoryService cs;

	@Autowired
	BookRepo br;
	
	@Autowired
	CategoryConverter catConv;
	
	@Override
	public void deleteCategory(Category category) {
		categoryRepo.delete(category);
	}
/*
	@Override
	public void deleteById(Long id) {
		categoryRepo.deleteById(id);
		
	}*/

	@Override
	public Category findCategoryById(long id) {
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> getCategories() {
		return (List<Category>) categoryRepo.findAll();
	}

	@Override
	public Category addCategory(String categoryName) {
		Category c = new Category();
		c.setName(categoryName);
		categoryRepo.save(c);
		return c;
		
	}
	@Override
	public Category clearCategory(long idCategory) {
		Category c= categoryRepo.findById(idCategory).get();
		c.getBooks().clear();
		return categoryRepo.save(c);
	}
	/*@Override
	public Category addBookToCategory(long idCategory, long idBook) {
		Book b = br.findById(idBook).get();
		Category c =categoryRepo.findById(idCategory).get();
		c.getBooks().add(b);
		categoryRepo.save(c);
		return c;
	}*/
	@Override
	public List<Book> getAllBooksInCategory(long idCategory){
		Category c =categoryRepo.findById(idCategory).get();
		return c.getBooks();
	}
	
	public Category removeBookFromCategory(long idCategory, long idBook ) {
		Category c = categoryRepo.findById(idCategory).get();
         for (Book book : c.getBooks()) {
        	 if (book.getId()== idBook) {
        		 c.getBooks().remove(book);
         }
         }
		categoryRepo.save(c);
		return c;
	}
	
	public boolean updateCategory (Category c) {
	
		if (categoryRepo.existsById(c.getId())) {
			
		categoryRepo.save(c);
		 
			return true;
		}
		
		return false;
		
	}
	
	public List<CategoryDTO> getCategoriesByNameAsc(){
		List<Category> categories =  categoryRepo.getCategoriesByNameAsc();
		
		return  catConv.entitiesToCategoryDTOs(categories);
	
	}
	
	
	public List<CategoryDTO> getCategoriesByNameDesc(){
		List<Category> categories =  categoryRepo.getCategoriesByNameDesc();
				
		return  catConv.entitiesToCategoryDTOs(categories);
				
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
