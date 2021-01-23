package com.project.repos;

import org.springframework.data.repository.CrudRepository;

import com.project.entities.Category;



public interface CategoryRepo extends CrudRepository<Category, Long>{

}
