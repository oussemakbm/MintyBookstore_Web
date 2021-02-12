package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.InteractionDTO;
import com.project.converter.InteractionConverter;
import com.project.entities.Interaction;
import com.project.security.UserUtilities;
import com.project.services.InteractionService;

@RestController
@RequestMapping("/interaction")
public class InteractionController {
	
	@Autowired
	UserUtilities userUtilities;
	@Autowired
	InteractionService interactionService;
	@Autowired
	InteractionConverter interactionConverter;
	
	@GetMapping(path="/{bookId}/all")
	public ResponseEntity<List<InteractionDTO>> getInteractions(@PathVariable("bookId") long bookId) {
		
		List<InteractionDTO> result = interactionConverter.entitiesToDTOs(interactionService.getBookInteractions(bookId));
		
		return new ResponseEntity<List<InteractionDTO>>(result, HttpStatus.OK);
	}
	
	
	@PostMapping(path="/{bookId}/add")
	public ResponseEntity<InteractionDTO> addOrUpdateInteraction(@RequestBody InteractionDTO interaction, @PathVariable("bookId") long bookId) {
		long userId = userUtilities.getCurrentUserId();
		Interaction i = interactionService.AddOrUpdateInteraction(userId, bookId, interaction.isLiked(), interaction.getRatingValue());
		InteractionDTO result = interactionConverter.entityToDTO(i);
		return new ResponseEntity<InteractionDTO>(HttpStatus.OK);
	}
	
	
}
