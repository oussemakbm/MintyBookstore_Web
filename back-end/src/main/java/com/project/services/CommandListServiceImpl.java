package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;

public class CommandListServiceImpl implements CommandListService{
	
	@Autowired
	private CommandLineService commandLineService;
	
	@Autowired
	private CommandListRepo commandListRepo;
	
	@Autowired
	UserRepo userRepo;

	@Override
	public CommandList updateCommandList(long idCommandList, String newStatus) {
		BigDecimal cartTotal = new BigDecimal(0);
		
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
      List<CommandLine> commandLineList = commandLineService.findByCommandList(idCommandList);
		
		for (CommandLine commandLine : commandLineList) {
			commandLine.setCommandlist(null);
			commandLineService.save(commandLine);
		
		CommandList c = commandListRepo.findById(idCommandList).get();
		c.setTotalPrice(new BigDecimal(0));
		commandListRepo.save(c);
		
		}
		
		
	}
	@Override
	public Long addCommandList(CommandList cml) {
		commandListRepo.save(cml);
		return cml.getId();
	}
	
	@Override
	public List<CommandList> getAllCommandListsByUser(long id){
		return userRepo.getUserCommandList(id);
		
	}
	@Override
	public void deleteCommandList(CommandList commandList) {
		commandListRepo.delete(commandList);
	}
	@Override
	public void deleteCommandListById(Long idCommandList) {
	   commandListRepo.deleteById(idCommandList);
	}
	

}
