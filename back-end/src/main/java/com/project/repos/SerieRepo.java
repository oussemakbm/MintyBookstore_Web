package com.project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Serie;

@Repository
public interface SerieRepo extends CrudRepository<Serie, Long>{

}
