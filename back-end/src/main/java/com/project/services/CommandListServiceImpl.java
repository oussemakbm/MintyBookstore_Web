package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.Status;
import com.project.repos.CommandListRepo;

public class CommandListServiceImpl implements CommandListService{
	
	@Autowired
	private CommandLineService commandLineService;
	
	@Autowired
	private CommandListRepo commandListRepo;

	@Override
	public CommandList updateCommandList(long idCommandList, Status newStatus) {
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CommandLine> commandLineList = commandLineService.findByCommandList(idCommandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLineService.updateCommandLine(commandLine);
			cartTotal =  cartTotal.add(commandLine.getPrice());
		}
		CommandList c = commandListRepo.findById(idCommandList).get();
		c.setTotalPrice(cartTotal);
		c.setStatus(newStatus);
		commandListRepo.save(c);
		
		return c;
	}

	@Override
    public void clearCommandList(long idCommandList) {
      List<CommandLine> commandLineList = commandLineService.findByCommandList(idCommandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLine.setCommandlist(null);
			commandLineService.save(commandLine);
		
		CommandList c = commandListRepo.findById(idCommandList).get();
		c.setTotalPrice(new BigDecimal(0));
		commandListRepo.save(c);
		
		}
		
	}
	
	public List<CommandList> getAllCommandListsByUser(long id){
		return null;
		
	}

}
