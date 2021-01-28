package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.AddBookToCommandLineDTO;
import com.project.DTOs.DeleteBookFromCommandLineDTO;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.services.CommandLineService;

@RestController
public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	
	@PostMapping("/api/commandLine/addBook")
	public ResponseEntity<CommandLine> addBookToCommandLine(@RequestBody AddBookToCommandLineDTO addBookCommandLineDTO) {
		CommandLine cml=commandLineService.addBookToCommandLine(addBookCommandLineDTO.getIdBook(),addBookCommandLineDTO.getUserId(), addBookCommandLineDTO.getQty(),addBookCommandLineDTO.getIdCommandList());
		if (cml!=null)
			return new ResponseEntity<CommandLine>(cml,HttpStatus.OK);
		return new ResponseEntity<CommandLine>(HttpStatus.BAD_REQUEST);

	}
	
	
	@DeleteMapping("/api/commandLine/deleteBook")
	public ResponseEntity<CommandLine> removeBookFromCommandLine(@RequestBody DeleteBookFromCommandLineDTO deleteBookFromCommandLineDTO){
		
		CommandLine cml = commandLineService.deleteBookFromCommandLine(deleteBookFromCommandLineDTO.getIdBook(), deleteBookFromCommandLineDTO.getIdCommandLine());
        return new ResponseEntity<CommandLine>(cml,HttpStatus.ACCEPTED);	
		
	}
	
	@GetMapping("/api/commandLine/getCommandLines/{id]")
	public ResponseEntity<List<CommandLine>> getAllCommandLinesByCommandList(@RequestParam("id") long idCommandList){
		
		List<CommandLine> cml = commandLineService.findByCommandList(idCommandList);
		return new ResponseEntity<List<CommandLine>>(cml, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/commandLine/getBookFromCommandLine/{id]")
	public ResponseEntity<Book> getBookInCommandLine(@RequestParam("id") long idCommandLine){
		Book book=commandLineService.getBookInCommadnLine(idCommandLine);
		
		return new ResponseEntity(book, HttpStatus.ACCEPTED);
		
    
	}
	
	
	
	
	
	
	
	
	
	
}
