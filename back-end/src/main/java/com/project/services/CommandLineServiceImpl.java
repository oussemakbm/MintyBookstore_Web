package com.project.services;

import java.math.BigDecimal;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import com.project.DTOs.CommandLineDTO;
import com.project.converter.CommandLineConverter;
import com.project.entities.Book;
import com.project.entities.CommandLine;
import com.project.entities.CommandList;
import com.project.entities.User;
import com.project.repos.BookRepo;
import com.project.repos.CommandLineRepo;
import com.project.repos.CommandLineSearchRepo;
import com.project.repos.CommandListRepo;
import com.project.repos.UserRepo;
import com.project.security.UserUtilities;
import com.project.util.PagingHeaders;
import com.project.util.PagingResponse;
import java.util.Objects;


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
	CommandLineSearchRepo clsRepo;
	
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
	/*public CommandLine get(long id) {
        return clsRepo.findById(id).get();
	}
	

	
	 
    public PagingResponse get(Specification<CommandLine> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<CommandLine> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }
    
    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }
    
    
    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }
    
    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
    
    */


}
