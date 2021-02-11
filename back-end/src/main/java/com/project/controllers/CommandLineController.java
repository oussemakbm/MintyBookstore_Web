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
import com.project.converter.CommandLineConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.services.CommandLineService;


import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;



@RestController
@RequestMapping(value="/commandLine")
public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	@Autowired
	CommandLineConverter commandLineConverter;
	/*
	@PostMapping("/commandLine/addBook")
	public ResponseEntity<CommandLine> addBookToCommandLine(@RequestBody CommandLineDTO addBookCommandLineDTO) {
		CommandLine cml=commandLineService.addBookToCommandLine(addBookCommandLineDTO );
		if (cml!=null)
			return new ResponseEntity<CommandLine>(cml,HttpStatus.OK);
		return new ResponseEntity<CommandLine>(HttpStatus.BAD_REQUEST);
	}*/
	
	@DeleteMapping("/updateCommandLine")
	public ResponseEntity<String> updateCommandLine(@RequestBody CommandLineDTO clDTO){
		if(commandLineService.updateCommandLine(clDTO)!= null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Saved Successfully !");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saved Failed !");
	}
	
	
	/*@DeleteMapping("/deleteBook/{idBook}/{idCommandLine}")
	public ResponseEntity<CommandLine> removeBookFromCommandLine(@PathVariable("idBook") long idBook ,@PathVariable("idCommandLine") long idCommandLine){
		CommandLine cml = commandLineService.deleteBookFromCommandLine( idBook, idCommandLine);
        return new ResponseEntity<CommandLine>(cml,HttpStatus.ACCEPTED);	
		
	}*/
	
	@GetMapping("/getCommandLines/{id]")
	public ResponseEntity<List<CommandLine>> getAllCommandLinesByCommandList(@RequestParam("id") long idCommandList){
		List<CommandLine> cml = commandLineService.findByCommandList(idCommandList);
		return new ResponseEntity<List<CommandLine>>(cml, HttpStatus.ACCEPTED);
	}
	
	/*@GetMapping("/getBookFromCommandLine/{id]")
	public ResponseEntity<Book> getBookInCommandLine(@RequestParam("id") long idCommandLine){
		Book book=commandLineService.getBookInCommadnLine(idCommandLine);
		
		return new ResponseEntity(book, HttpStatus.ACCEPTED);

	}*/
	
}
