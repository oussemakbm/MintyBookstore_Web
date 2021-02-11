package com.project.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
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
public class CommandLineController {

	@Autowired
	CommandLineService commandLineService;
	/*
	@PostMapping("/commandLine/addBook")
	public ResponseEntity<CommandLine> addBookToCommandLine(@RequestBody CommandLineDTO addBookCommandLineDTO) {
		CommandLine cml=commandLineService.addBookToCommandLine(addBookCommandLineDTO );
		if (cml!=null)
			return new ResponseEntity<CommandLine>(cml,HttpStatus.OK);
		return new ResponseEntity<CommandLine>(HttpStatus.BAD_REQUEST);

	}*/
	
	
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
	
	/*@PostMapping("/commandLine/CreateCommandLine")
	public ResponseEntity<String> CreateCommandLine(@RequestBody long idCommandList, CommandLineDTO clDTO){
		
		boolean b = commandLineService.addCommandLine(idCommandList, clDTO);
		if(b==true)
		 return ResponseEntity<String>(b,HttpStatus.ACCEPTED).Body("CommandLine Created Succesfully !!");
	}*/
	
	@GetMapping("/commandLine/topFiveBooks")
	public ResponseEntity<List<Book>> getTopFiveBooks(){
		List<Book> topBooks = commandLineService.gettopfiveofbooks();
		return new ResponseEntity<List<Book>>(topBooks,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	
	
	
	
	
}
