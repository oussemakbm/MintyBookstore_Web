package com.project.services;

import java.math.BigDecimal;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




import com.project.DTOs.CommandLineDTO;
import com.project.converter.CommandLineConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import org.springframework.data.domain.Sort;
import com.project.repos.BookRepo;
import com.project.repos.CommandLineRepo;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;
import com.project.security.UserUtilities;




@Service
public class CommandLineServiceImpl implements CommandLineService {
	@Autowired
	private CommandLineRepo commandLineRepo;
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	CommandListRepo commandListRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	CommandListService clmService;
	
	
	
	@Autowired
	UserUtilities userUtilities;
	

	
	@Autowired
	CommandLineConverter clConverter;
	

	@Override
	public List<CommandLine> findByCommandList(long idCommandList) {
		CommandList commandlist=  commandListRepo.findById(idCommandList).get();
		return commandlist.getCommandLines();
	}

	@Override
	public CommandLine findByid(long id) {
		return commandLineRepo.findById(id).get();
		
	}
	
	public boolean addCommandLine(long idCommandList, CommandLineDTO clDTO) {
		if(commandListRepo.existsById(idCommandList)) {
			
		
		CommandList cml = commandListRepo.findById(idCommandList).get();
		
		Book book = bookRepo.findById(clDTO.getBookId()).get();
		
		CommandLine cl = clConverter.DTOToentity(clDTO);
		cl.setBook(book);
	    cml.getCommandLines().add(cl);
		commandListRepo.save(cml);
		
		
		return true;
		
		
	}
		return false;
	
	}
	
	public boolean deleteCommandLine(long idCommandLine, long idCommandList) {
		if(commandLineRepo.existsById(idCommandLine) && commandListRepo.existsById(idCommandList)) {
				CommandLine cl = commandLineRepo.findById(idCommandLine).get();
				CommandList cml =commandListRepo.findById(idCommandList).get();
				cml.getCommandLines().remove(cl);
				commandListRepo.save(cml);
		return true;
				
				
		}
		return false;
	}
	
/*
	@Override
	public CommandLine addBookToCommandLine(CommandLineDTO commandLine) {
		
		CommandList cl = commandListRepo.findById(commandLine.getCommandListId()).get();
		List<CommandList> commandLists  = userRepo.getUserCommandList(userId);
		CommandList c = new CommandList();
		for (CommandList commandList :  commandLists) {
			if (commandList.getId() == commandLine.getCommandListId()) {
				c=commandList;
				break;
			}
		}
			
				CommandLine commandLine = new CommandLine();
				commandLine.setCommandlist(c);
				commandLine.setBook(bookRepo.findById(idBook).get());
				
				commandLine.setQuantity(qty);
				commandLine.setPrice(new BigDecimal (bookRepo.findById(idBook).get().getPrix()).multiply(new BigDecimal(qty)));
				commandLine = commandLineRepo.save(commandLine);
				c.setTotalPrice(commandLine.getPrice().add(c.getTotalPrice()));
				commandListRepo.save(c);
				
				
				
				return commandLine;
				
	}*/
	@Override
	public Book getBookInCommadnLine(long id) {
		CommandLine cml = commandLineRepo.findById(id).get();
		return cml.getBook();
	}


	@Override
	public void removeCommandLine(CommandLine commandLine) {
		commandLineRepo.delete(commandLine);
	}

	@Override
	public CommandLine save(CommandLine commandLine) {
		return commandLineRepo.save(commandLine);
	}
	@Override
	public CommandLine deleteBookFromCommandLine(long idCommandLine, long idBook) {
		CommandLine cml= commandLineRepo.findById(idCommandLine).get();
		
		Book book = cml.getBook();
	    if (book.getId()==idBook) {
	    	cml.setBook(null);
	    	
	    	
	    }
	    commandLineRepo.save(cml);
	    return cml;
	}

	@Override
	public CommandLine updateCommandLine(CommandLine commandLine) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> gettopfiveofbooks(){
		return commandLineRepo.gettopfiveofbooks();
	}
	
	


}
