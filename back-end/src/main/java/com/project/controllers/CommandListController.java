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

import com.project.DTOs.CommandLineDTO;
import com.project.DTOs.CommandListDTO;
import com.project.DTOs.SerieDTO;
import com.project.converter.CommandLineConverter;
import com.project.converter.CommandListConverter;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.Serie;
import com.project.entities.Status;
import com.project.security.UserUtilities;
import com.project.services.CommandListService;
import com.sipios.springsearch.anotation.SearchSpec;

@Controller
public class CommandListController {
	
	//private CommandListSearchRepo commandListSearchRepo;
	
	@Autowired
	CommandLineConverter clConverter;
	@Autowired
	CommandListService commandListService;
	@Autowired
	CommandListConverter clmConverter;
	
	
	/*@GetMapping("/CommandLists/")
	public ResponseEntity<List<CommandList>> searchForCommandLists(@SearchSpec Specification<CommandList> specs){
		return new ResponseEntity<>(commandListSearchRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	}*/
	
	@GetMapping("/commandList/all/{id}")
	public ResponseEntity <List<CommandList>> getAllCommandListsByUser (){
		List<CommandList> cml = commandListService.getCommandListsByIdUser();
		if (cml!=null)
			return new ResponseEntity<List<CommandList>>(cml,HttpStatus.OK);
		return new ResponseEntity<List<CommandList>>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addCommandList")
	public ResponseEntity<String> addCommandList(@RequestBody CommandLineDTO clDTO){
	
		
		//CommandLine cl = clConverter.DTOToentity(clDTO);
	    commandListService.addCommandList(clDTO);
	    
	/*	if(Objects.isNull(cl.getId()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error ! CommandList failed");*/		
		return   ResponseEntity.status(HttpStatus.OK).
				body("CommandList added Successfully !");
	}

		
	@DeleteMapping("/deleteCommandList/{idCommandList}")
		public ResponseEntity<String> deleteCommandList(@PathVariable("idCommandList") long idCommandList){
			commandListService.clearCommandList(idCommandList);
			return ResponseEntity.status(HttpStatus.OK)
			        .body("Deleted Successfully !");}
	
	@PutMapping(value="/commandList/updateCommandList")
	public ResponseEntity<String> updateCommandList(@RequestBody CommandListDTO clDTO){
		CommandList cl = clmConverter.DTOToentity(clDTO);
		commandListService.updateCommandList(cl.getId(), cl.getStatus());
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Updated Successfully !");
	}
	
	@GetMapping("/getCommandListByStatus/{status}")
	public ResponseEntity<List<CommandListDTO>> getCommandListsByStatus(@PathVariable ("status") Status status){
		
				List<CommandListDTO> clDTOs = commandListService.getCommandListsByStatus(status);
				if (clDTOs!=null)
					return new ResponseEntity<List<CommandListDTO>>(clDTOs,HttpStatus.OK);
				return new ResponseEntity<List<CommandListDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	

}
