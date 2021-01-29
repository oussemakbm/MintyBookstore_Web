package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.RemoveBookFromCategoryDTO;
import com.project.entities.Category;
import com.project.entities.Wishlist;
import com.project.services.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService cs;
	
	@DeleteMapping("/api/category/removeBook")
	public ResponseEntity<Category> removeBookFromCategory(@RequestBody RemoveBookFromCategoryDTO removeBookDTO) {
		Category c=cs.removeBookFromCategory(removeBookDTO.getIdCategory(), removeBookDTO.getIdBook());
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/api/category/add/{name}")
	public ResponseEntity<Category> addCategory(@PathVariable("name") String name) {
		Category c=cs.addCategory(name);
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);

	}
	
	

}
