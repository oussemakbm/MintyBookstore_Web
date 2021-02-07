package com.project.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DTOs.CommandListDTO;
import com.project.DTOs.SerieDTO;
import com.project.converter.CommandListConverter;
import com.project.entities.CommandList;
import com.project.entities.Serie;
import com.project.repos.CommandListSearchRepo;
import com.project.services.CommandListService;
import com.sipios.springsearch.anotation.SearchSpec;

@Controller
public class CommandListController {
	
	private CommandListSearchRepo commandListSearchRepo;
	
	
	@Autowired
	CommandListService commandListService;
	@Autowired
	CommandListConverter clConverter;
	
	@GetMapping("/CommandLists/")
	public ResponseEntity<List<CommandList>> searchForCommandLists(@SearchSpec Specification<CommandList> specs){
		return new ResponseEntity<>(commandListSearchRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	}
	
	@GetMapping("/commandList/all/{id}")
	public ResponseEntity <List<CommandList>> getAllCommandListsByUser (@RequestParam ("id") long idUser){
		List<CommandList> cml = commandListService.getAllCommandListsByUser(idUser);
		if (cml!=null)
			return new ResponseEntity<List<CommandList>>(cml,HttpStatus.OK);
		return new ResponseEntity<List<CommandList>>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addCommandList")
	public ResponseEntity<CommandListDTO> addCommandList(@RequestBody CommandListDTO clDTO){
		CommandList cl = clConverter.DTOToentity(clDTO);
	    commandListService.addCommandList(cl);
		if(Objects.isNull(cl.getId()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
		return  new ResponseEntity<CommandListDTO>(clConverter.entityToDTO(cl), HttpStatus.OK);
	}

		
	@DeleteMapping("/deleteCommandList/{idCommandList}")
		public ResponseEntity<String> deleteCommandList(@PathVariable("idCommandList") long idCommandList){
			commandListService.deleteCommandListById(idCommandList);
			return ResponseEntity.status(HttpStatus.OK)
			        .body("Deleted Successfully !");}
	
	@PutMapping(value="/updateSerie")
	public ResponseEntity<String> updateSerie(@RequestBody CommandListDTO clDTO){
		CommandList cl = clConverter.DTOToentity(clDTO);
		commandListService.updateCommandList(cl.getId(), cl.getStatus());
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Updated Successfully !");
	}
	
	
	

}
