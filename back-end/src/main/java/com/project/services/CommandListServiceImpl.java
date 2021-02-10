package com.project.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.DTOs.CommandLineDTO;
import com.project.converter.CommandLineConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.Status;
import com.project.repos.BookRepo;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;
import com.project.security.UserUtilities;

@Service
public class CommandListServiceImpl implements CommandListService{
	
	@Autowired
	private CommandLineService commandLineService;
	
	@Autowired
	private CommandListRepo commandListRepo;
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserUtilities userUtilities;
	
	@Autowired
	BookRepo bRepo;
	
	@Autowired
	CommandLineConverter clConverter;

	@Override
	public CommandList updateCommandList(long idCommandList, Status newStatus) {
		BigDecimal cartTotal = new BigDecimal(0);
		
		CommandList cl = commandListRepo.findById(idCommandList).get();
		
		
		List<CommandLine> commandLineList = commandLineService.findByCommandList(idCommandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLineService.updateCommandLine(commandLine);
			cartTotal =  cartTotal.add(commandLine.getPrice());
		}
		CommandList c = commandListRepo.findById(idCommandList).get();
		c.setTotalPrice(cartTotal);
		c.setStatus(commandListRepo.findById(idCommandList).get().getStatus());
		commandListRepo.save(c);
		
		return c;
	}
	

	@Override
    public void clearCommandList(long idCommandList) {
      CommandList cl = commandListRepo.findById(idCommandList).get();
		commandListRepo.delete(cl);
		/*for (CommandLine commandLine : commandLineList) {
			commandLine.setCommandlist(null);
			commandLineService.save(commandLine);
		
		CommandList c = commandListRepo.findById(idCommandList).get();
		c.setTotalPrice(new BigDecimal(0));
		commandListRepo.save(c);*/
		
		
		
		
	}
	@Override
	public Long addCommandList(CommandLineDTO clDTO ) {
		CommandList cml = new CommandList();
		long userId= userUtilities.getCurrentUserId();
		List<CommandLine> commandLines = new ArrayList<CommandLine>();
		CommandLine cl = clConverter.DTOToentity(clDTO);
		cl.setCommandlist(cml);
		commandLines.add(cl);
		cml.setCommandLines(commandLines);
		//cml.getCommandLines().add(cl);
		Book book = bRepo.findById(clDTO.getBookId()).get();
		cl.setBook(book);
		
		
		cml.setUser(userRepo.findById(userId).get());	
		cml.setStatus(Status. WaitingValidating);
		cml.setTotalPrice(null);
		commandListRepo.save(cml);
		return cml.getId();
	}
	
	@Override
	public List<CommandList> getCommandListsByIdUser(){
		long idUser= userUtilities.getCurrentUserId();
		return commandListRepo.getCommandListsByIdUser(idUser);
		
	}
	
	

}
