package com.project.controllers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTOs.CommandLineDTO;
import com.project.converter.BookConverter;
import com.project.converter.CommandLineConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
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
	BookConverter bConverter;
	
	/*@PostMapping("/commandLine/addBook")
	public ResponseEntity<CommandLine> addBookToCommandLine(@RequestBody CommandLineDTO addBookCommandLineDTO) {
		CommandLine cml=commandLineService.addBookToCommandLine(addBookCommandLineDTO );
		if (cml!=null)
			return new ResponseEntity<CommandLine>(cml,HttpStatus.OK);
		return new ResponseEntity<CommandLine>(HttpStatus.BAD_REQUEST);
	}*/
	
	@DeleteMapping("/updateCommandLine")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
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
	
	@GetMapping("/getCommandLines/{id}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<CommandLine>> getAllCommandLinesByCommandList(@RequestParam("id") long idCommandList){
		List<CommandLine> cml = commandLineService.findByCommandList(idCommandList);
		return new ResponseEntity<List<CommandLine>>(cml, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getBookFromCommandLine/{id]")
	public ResponseEntity<Book> getBookInCommandLine(@RequestParam("id") long idCommandLine){
		Book book=commandLineService.getBookInCommadnLine(idCommandLine);
		
		return new ResponseEntity(book, HttpStatus.ACCEPTED);


	}
	/*
	@PostMapping("/commandLine/CreateCommandLine")
	public ResponseEntity<String> CreateCommandLine(@RequestBody long idCommandList, CommandLineDTO clDTO){
		
		boolean b = commandLineService.addCommandLine(idCommandList, clDTO);
		if(b==true)
		 return ResponseEntity<String>(b,HttpStatus.ACCEPTED).Body("CommandLine Created Succesfully !!");
	}*/
	
	@GetMapping("/topFiveBooks")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<List<String>> getTopFiveBooks() {
		List<String> topBooks = commandLineService.gettopfiveofbooks();
		return new ResponseEntity<List<String>>(topBooks,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/createCommandLine")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<CommandLineDTO> CreateCommandLine(@RequestBody CommandLineDTO clDTO) {
		if (commandLineService.addCommandLine(clDTO))
			return new ResponseEntity<CommandLineDTO>(clDTO,HttpStatus.OK);
		return new ResponseEntity<CommandLineDTO>(HttpStatus.BAD_REQUEST);
		

	}
	
	
	@DeleteMapping("/deleteCommadLine/{idCommandLine}/{idCommandList}")
	@PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
	public ResponseEntity<String>deleteCommandLine(@PathVariable("idCommandLine" )long idCommandLine ,@PathVariable("idCommandList") long idCommandList) {
		if (commandLineService.deleteCommandLine(idCommandLine, idCommandList))
		   return ResponseEntity.status(HttpStatus.OK)
		        .body("Deleted Successfully !");
		   return ResponseEntity.status(HttpStatus.OK).body("Deleting CommandLine is failed");
	}
	
	
	
	
}
		

	
	

