package com.project.repos;

import org.springframework.data.repository.CrudRepository;

import com.project.entities.Interaction;

public interface InteractionRepo extends CrudRepository<Interaction, Long> {
		
}
