package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.project.entities.CommandList;
import com.project.repos.CommandListSearchRepo;
import com.project.services.CommandListService;
import com.sipios.springsearch.anotation.SearchSpec;

@Controller
public class CommandListController {
	
	private CommandListSearchRepo commandListSearchRepo;
	
	@Autowired
	CommandListService commandListService;
	
	
	@GetMapping("/api/CommandLists")
	public ResponseEntity<List<CommandList>> searchForCommandLists(@SearchSpec Specification<CommandList> specs){
		return new ResponseEntity<>(commandListSearchRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	}
	
	@GetMapping("/api/commandList/all/{id}")
	public ResponseEntity <List<CommandList>> getAllCommandListsByUser (@RequestParam ("id") long idUser){
		List<CommandList> cml = commandListService.getAllCommandListsByUser(idUser);
		if (cml!=null)
			return new ResponseEntity<List<CommandList>>(cml,HttpStatus.OK);
		return new ResponseEntity<List<CommandList>>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	

}
