package com.project.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTOs.CommandLineDTO;
import com.project.DTOs.CommandListDTO;
import com.project.DTOs.UserDTO;
import com.project.converter.CommandLineConverter;
import com.project.converter.CommandListConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.Status;
import com.project.entities.User;
import com.project.repos.BookRepo;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;
import com.project.security.UserUtilities;

@Service
public class CommandListServiceImpl implements CommandListService{
	
	@Autowired
	CommandLineService commandLineService;
	@Autowired
	CommandListRepo commandListRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserUtilities userUtilities;
	@Autowired
	BookRepo bRepo;
	@Autowired
	CommandLineConverter clConverter;
	@Autowired
	BookService bookService;
	@Autowired
	CommandListConverter clmConverter;
	
	@Override
	@Transactional
	public String saveCommandList(long idCommandList) {
		if(commandListRepo.existsById(idCommandList))
			return "Command list is not found";
		CommandList cls = commandListRepo.findById(idCommandList).get();
		if(cls.getStatus() == Status.WaitingValidating)
			return "Command list is in "+cls.getStatus()+ " status";
		BigDecimal cartTotal = new BigDecimal(0);
		List<CommandLine> commandLineList = commandLineService.findByCommandList(idCommandList);
		for (CommandLine cl : commandLineList) {
			if(bookService.reduceQuantity(cl.getBook().getId(),cl.getQuantity())){	
				return null;
			}
			BigDecimal A = new BigDecimal(cl.getQuantity());
	        BigDecimal B = new BigDecimal(cl.getBook().getPrix());
	        cartTotal = cartTotal.add(A.multiply(B));			
		}
		cls.setTotalPrice(cartTotal);
		cls.setStatus(Status.Validated);
		cls.setSavedDate(LocalDateTime.now());
		commandListRepo.save(cls);
		return "Saved Successfully !";
	}

	@Override
    public void clearCommandList(long idCommandList) {
      CommandList cl = commandListRepo.findById(idCommandList).get();
		commandListRepo.delete(cl);
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
		Book book = bRepo.findById(clDTO.getBookId()).get();
		cl.setBook(book);
		cml.setUser(userRepo.findById(userId).get());	
		cml.setStatus(Status.WaitingValidating);
		cml.setCreatedDate(LocalDateTime.now());
		cml.setTotalPrice(null);
		commandListRepo.save(cml);
		return cml.getId();
	}
	
	@Override
	public List<CommandList> getCommandListsByIdUser(){
		long idUser= userUtilities.getCurrentUserId();
		return commandListRepo.getCommandListsByIdUser(idUser);
		
	}
	
	@Override
	public List<CommandListDTO> getCommandLists(String search){
		List<CommandList> commandLists=commandListRepo.getCommandListsByStatus("%"+search+"%");
		return clmConverter.entitiesToCommandListsDTOs(commandLists);
	}
	

}
