package com.project.controllers;


import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.DTOs.CategoryDTO;
import com.project.DTOs.NumCategoryDTO;
import com.project.converter.CategoryConverter;
import com.project.entities.Category;
import com.project.repos.CategoryRepo;
import com.project.services.CategoryService;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	CategoryService cs;
	
	@Autowired
	CategoryRepo cRepo;
	
	@Autowired
	CategoryConverter categoryConverter;
	
	/*@DeleteMapping("/deleteCategory")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Category> removeBookFromCategory(@RequestBody RemoveBookFromCategoryDTO removeBookDTO) {
		Category c=cs.removeBookFromCategory(removeBookDTO.getIdCategory(), removeBookDTO.getIdBook());
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	}*/
	
	@PostMapping("/addCategory/{name}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CategoryDTO> addCategory(@PathVariable("name") String name) {
		Category c=cs.addCategory(name);
		if (c!=null)
			return new ResponseEntity<CategoryDTO>(categoryConverter.entityToDTO(c),HttpStatus.OK);
		return new ResponseEntity<CategoryDTO>(HttpStatus.BAD_REQUEST);

	}
	/*@PostMapping("/addBook")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Category> addBookToCategory(@RequestBody AddBookToCategoryDTO addBookCategoryDTO) {
		Category c=cs.addBookToCategory(addBookCategoryDTO.getIdBook(),addBookCategoryDTO.getIdCategory());
		if (c!=null)
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);

	}*/
	
	@DeleteMapping("/deleteCategoryById/{idCategory}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("idCategory") long idCategory){
		Category c = cRepo.findById(idCategory).get();
		cs.deleteCategory(c);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");}
	
	@GetMapping("/getCategories")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List <Category> categories = cs.getCategories();
		if(categories!=null)
			return new ResponseEntity<List<CategoryDTO>>(categoryConverter.entitiesToCategoryDTOs(categories),HttpStatus.OK);
		return new ResponseEntity<List<CategoryDTO>>(HttpStatus.BAD_REQUEST);
				
	}
	
	@PostMapping("/updateCategory")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO cDTO){
		Category c =categoryConverter.DTOToentity(cDTO);
		if (cs.updateCategory(c)) {
				return ResponseEntity.status(HttpStatus.OK)
				.body("Category updated Successfully !");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update failed !!");
		
		}
	
	
	@GetMapping("/getCategories/OrderASC")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CategoryDTO>> getCategoryOrderedAsc(){
		List<CategoryDTO> categoriesDTOs = cs.getCategoriesByNameAsc();
		if (categoriesDTOs!=null)
			return new ResponseEntity<List<CategoryDTO>>(categoriesDTOs,HttpStatus.OK);
		return new ResponseEntity<List<CategoryDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getCategories/OrderDESC")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CategoryDTO>> getCategoryOrderedDesc(){
		List<CategoryDTO> categoriesDTOs = cs.getCategoriesByNameDesc();
		if (categoriesDTOs!=null)
			return new ResponseEntity<List<CategoryDTO>>(categoriesDTOs,HttpStatus.OK);
		return new ResponseEntity<List<CategoryDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/getNumberOfBooksByCategory/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> getNumBooksByCat(@PathParam("id") int id){
		String msg =cs.getNumberOfBooksByCategory(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	


