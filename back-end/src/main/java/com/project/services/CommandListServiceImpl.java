package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.repos.CommandListRepo;

public class CommandListServiceImpl implements CommandListService{
	
	@Autowired
	private CommandLineService commandLineService;
	
	@Autowired
	private CommandListRepo commandListRepo;

	@Override
	public CommandList updateCommandList(CommandList commandList) {
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CommandLine> commandLineList = commandLineService.findByCommandList(commandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLineService.updateCommandLine(commandLine);
			cartTotal =  cartTotal.add(commandLine.getPrice());
		}
		
		commandList.setTotalPrice(cartTotal);
		commandListRepo.save(commandList);
		
		return commandList;
	}

	@Override
	public void clearCommandList(CommandList commandList) {
List<CommandLine> commandLineList = commandLineService.findByCommandList(commandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLine.setCommandlist(null);
			commandLineService.save(commandLine);
		
		
		commandList.setTotalPrice(new BigDecimal(0));
		commandListRepo.save(commandList);
		
		}
		
	}

}
