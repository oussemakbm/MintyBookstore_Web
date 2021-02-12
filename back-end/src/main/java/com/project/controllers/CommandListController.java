package com.project.controllers;

import java.util.List;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	UserUtilities userUtilities ;
	@Autowired
	CommandListConverter clmConverter;
	
	
	/*@GetMapping("/CommandLists/")
	public ResponseEntity<List<CommandList>> searchForCommandLists(@SearchSpec Specification<CommandList> specs){
		return new ResponseEntity<>(commandListSearchRepo.findAll(Specification.where(specs)), HttpStatus.OK);
	}*/
	
	@GetMapping("/commandList/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CommandListDTO>> getAllCommandListsByUser (){
		List<CommandList> cml = commandListService.getCommandListsByIdUser();
		if (cml!=null)
			return new ResponseEntity<List<CommandListDTO>>(clmConverter.entityToDTOs(cml),HttpStatus.OK);
		return new ResponseEntity<List<CommandListDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	/*@PostMapping("/addCommandList")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<String> addCommandList(@RequestBody CommandLineDTO clDTO){
	    commandListService.addCommandList(clDTO);	
		return   ResponseEntity.status(HttpStatus.OK).
				body("CommandList added Successfully !");
	}*/

		
	@DeleteMapping("/deleteCommandList/{idCommandList}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
		public ResponseEntity<String> deleteCommandList(@PathVariable("idCommandList") long idCommandList){
			commandListService.clearCommandList(idCommandList);
			return ResponseEntity.status(HttpStatus.OK)
			        .body("Deleted Successfully !");}
	
	@PutMapping(value="/saveCommandList/{idCommandList}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<String> saveCommandList(@PathVariable("idCommandList") long idCommandList){
		//CommandList cl = clmConverter.DTOToentity(clDTO);
		commandListService.saveCommandList(idCommandList);
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
