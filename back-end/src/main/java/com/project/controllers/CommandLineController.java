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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.services.CommandLineService;

@RestController
public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	
	@PostMapping("/commandLine/addBook")
	public ResponseEntity<CommandLine> addBookToCommandLine(@RequestBody CommandLineDTO addBookCommandLineDTO) {
		CommandLine cml=commandLineService.addBookToCommandLine(addBookCommandLineDTO.getIdBook(),addBookCommandLineDTO.getUserId(), addBookCommandLineDTO.getQty(),addBookCommandLineDTO.getIdCommandList());
		if (cml!=null)
			return new ResponseEntity<CommandLine>(cml,HttpStatus.OK);
		return new ResponseEntity<CommandLine>(HttpStatus.BAD_REQUEST);

	}
	
	
	@DeleteMapping("/commandLine/deleteBook/{idBook}/{idCommandLine}")
	public ResponseEntity<CommandLine> removeBookFromCommandLine(@PathVariable("idBook") long idBook ,@PathVariable("idCommandLine") long idCommandLine){
		
		CommandLine cml = commandLineService.deleteBookFromCommandLine( idBook, idCommandLine);
        return new ResponseEntity<CommandLine>(cml,HttpStatus.ACCEPTED);	
		
	}
	
	@GetMapping("/commandLine/getCommandLines/{id]")
	public ResponseEntity<List<CommandLine>> getAllCommandLinesByCommandList(@RequestParam("id") long idCommandList){
		
		List<CommandLine> cml = commandLineService.findByCommandList(idCommandList);
		return new ResponseEntity<List<CommandLine>>(cml, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/commandLine/getBookFromCommandLine/{id]")
	public ResponseEntity<Book> getBookInCommandLine(@RequestParam("id") long idCommandLine){
		Book book=commandLineService.getBookInCommadnLine(idCommandLine);
		
		return new ResponseEntity(book, HttpStatus.ACCEPTED);
		
		
    
	}
	
	
	
	
	
	
	
	
	
	
}
