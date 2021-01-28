package com.project.services;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.User;
import com.project.repos.CommandLineRepo;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;


@Service
public class CommandLineServiceImpl implements CommandLineService {
	@Autowired
	private CommandLineRepo commandLineRepo;
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	CommandListRepo commandListRepo;

	/*@Override
	public List<CommandLine> findByCommandList(long idCommandList) {
		
		return commandLineRepo.findByCommandList(idCommandList);
	}*/

	@Override
	public CommandLine findByid(long id) {
		return commandLineRepo.findById(id).get();
		
	}

	@Override
	public CommandLine addBookToCommandLine(Book book, long userId, int qty, long idCommandList) {
		List<CommandList> commandLists  = userRepo.getUserCommandList(userId);
		CommandList c = new CommandList();
		for (CommandList commandList :  commandLists) {
			if (commandList.getId() == idCommandList) {
				c=commandList;
				break;
			}
		}
			
				CommandLine commandLine = new CommandLine();
				commandLine.setCommandlist(c);
				commandLine.setBook(book);
				
				commandLine.setQuantity(qty);
				commandLine.setPrice(new BigDecimal (book.getPrix()).multiply(new BigDecimal(qty)));
				commandLine = commandLineRepo.save(commandLine);
				c.setTotalPrice(commandLine.getPrice().add(c.getTotalPrice()));
				commandListRepo.save(c);
				
				/*BookToCommandLine bookToCommandLine = new BookToCommandLine();
				
				bookToCommandLine.setBook(book);
				bookToCommandLine.setCommandLine(commandLine);
				bookToCommandLineRepo.save(bookToCommandLine);*/
				
				return commandLine;
				
	}
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


}
