package com.project.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.AddBookToCategoryDTO;
import com.project.DTOs.RemoveBookFromCategoryDTO;
import com.project.entities.Category;
import com.project.services.CategoryService;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	CategoryService cs;
	
	@DeleteMapping("/deleteCategory")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Category> removeBookFromCategory(@RequestBody RemoveBookFromCategoryDTO removeBookDTO) {
		Category c=cs.removeBookFromCategory(removeBookDTO.getIdCategory(), removeBookDTO.getIdBook());
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addCategory/{name}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Category> addCategory(@PathVariable("name") String name) {
		Category c=cs.addCategory(name);
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);

	}
	@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Category> addBookToCategory(@RequestBody AddBookToCategoryDTO addBookCategoryDTO) {
		Category c=cs.addBookToCategory(addBookCategoryDTO.getIdBook(),addBookCategoryDTO.getIdCategory());
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);

	}
	@DeleteMapping("/deleteCategoryById/{idCategory}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("idCategoryt") long idCategory){
		cs.deleteById(idCategory);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");}
	

	
	
	

}
