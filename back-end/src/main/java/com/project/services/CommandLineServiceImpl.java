package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.BookToCommandLine;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.User;
import com.project.repos.BookToCommandLineRepo;
import com.project.repos.CommandLineRepo;
import com.project.repos.CommandListRepo;
import java.util.ArrayList;

@Service
public class CommandLineServiceImpl implements CommandLineService {
	
	
	@Autowired
	private CommandLineRepo commandLineRepo;
	@Autowired
	private BookToCommandLineRepo bookToCommandLineRepo;

	@Override
	public List<CommandLine> findByCommandList(CommandList commandList) {
//		Commenting this so i can run the project
//		return commandLineRepo.findByCommandList(commandList);
		return new ArrayList<CommandLine>();
	}

	@Override
	public CommandLine findByid(long id) {
		return commandLineRepo.findById(id).get();
		
	}

	@Override
	public CommandLine addBookToCommandLine(Book book, User user, int qty) {
//		Commenting this so i can run the project
//	List<CommandLine> commandLineList = findByCommandList(user.getCommandList());
//				
//				for (CommandLine commandLine : commandLineList) {
//					if (book.getId() == commandLine.getBook().getId()) {
//						commandLine.setQuantity(commandLine.getQuantity()+qty);
//						commandLine.setPrice(new BigDecimal (book.getPrix()).multiply(new BigDecimal(qty)));
//					    commandLineRepo.save(commandLine);
//					}
//				
//	}
//				CommandLine commandLine = new CommandLine();
//				commandLine.setCommandList(user.getCommandList());
//				commandLine.setBook(book);
//				
//				commandLine.setQuantity(qty);
//				commandLine.setPrice(new BigDecimal (book.getPrix()).multiply(new BigDecimal(qty)));
//				commandLine = commandLineRepo.save(commandLine);
//				
//				BookToCommandLine bookToCommandLine = new BookToCommandLine();
//				
//				bookToCommandLine.setBook(book);
//				bookToCommandLine.setCommandLine(commandLine);
//				bookToCommandLineRepo.save(bookToCommandLine);
//	
		return new CommandLine();
	}

	@Override
	public CommandLine updateCommandLine(CommandLine commandLine) {
		return new CommandLine();
	}

	@Override
	public void removeCommandLine(CommandLine commandLine) {
		commandLineRepo.delete(commandLine);
	}

	@Override
	public CommandLine save(CommandLine commandLine) {
		return commandLineRepo.save(commandLine);
	}


}
