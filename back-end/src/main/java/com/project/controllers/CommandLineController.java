package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.CommandLineDTO;
import com.project.DTOs.CommandListDTO;
import com.project.converter.BookConverter;
import com.project.converter.CommandLineConverter;
import com.project.converter.CommandListConverter;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.services.CommandLineService;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value="/commandLine")
public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	@Autowired
	CommandLineConverter commandLineConverter;
	@Autowired
	CommandListConverter commandListConverter;
	
	@Autowired
	BookConverter bConverter;
	
	@PostMapping("/addCommandLine/{idbook}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<String> CreateCommandLine(@PathVariable("idbook") long idbook) {
		String ch = commandLineService.addCommandLine(idbook);
		return  ResponseEntity.status(HttpStatus.OK).body(ch);
	}
	
	@DeleteMapping("/deleteCommadLine/{idCommandLine}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<String>deleteCommandLine(@PathVariable("idCommandLine")long idCommandLine) {
		if (commandLineService.deleteCommandLine(idCommandLine))
		   return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");
		return ResponseEntity.status(HttpStatus.OK).body("Deleting CommandLine is failed");
	}
	
	@PostMapping("/updateCommandLine/{idCommandLine}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<CommandListDTO> updateCommandLine(@PathVariable("idCommandLine")long idCommandLine,@RequestParam("q") int q){
		CommandList cl = commandLineService.updateCommandLine(idCommandLine,q);
		if(cl!= null)
			return new ResponseEntity<CommandListDTO>(commandListConverter.entityToDTO(cl),HttpStatus.ACCEPTED);
		return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getCommandLines/{id}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<CommandLineDTO>> getAllCommandLinesByCommandList(@PathVariable ("id") int idCommandList){
		List<CommandLine> cml = commandLineService.findByCommandList(idCommandList);
		return new ResponseEntity<List<CommandLineDTO>>(commandLineConverter.entityToDTOs(cml), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/topFiveBooks")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<String>> getTopFiveBooks() {
		List<String> topBooks = commandLineService.gettopfiveofbooks();
		return new ResponseEntity<List<String>>(topBooks,HttpStatus.ACCEPTED);
		
	}
	
}