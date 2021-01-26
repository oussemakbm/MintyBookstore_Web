package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.DTOs.DeleteBookFromCommandLineDTO;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.services.CommandLineService;


public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	
	
	@DeleteMapping("/api/commandLine/deleteCommandLine")
	public ResponseEntity<CommandLine> deleteCommandLine(@RequestBody DeleteBookFromCommandLineDTO deleteBookFromCommandLineDTO){
		
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
