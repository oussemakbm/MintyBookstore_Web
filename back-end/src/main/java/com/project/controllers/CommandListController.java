package com.project.controllers;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.entities.CommandList;
import com.project.repos.CommandListSearchRepo;
import com.sipios.springsearch.anotation.SearchSpec;

@Controller
public class CommandListController {
	
	private CommandListSearchRepo commandListSearchRepo;
	
	
	@GetMapping("/api/CommandLists")
	public ResponseEntity<List<CommandList>> searchForCommandLists(@SearchSpec Specification<CommandList> specs){
		return new ResponseEntity<>(commandListSearchRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	}
	
	

}
