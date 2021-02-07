package com.project.DTOs;

import java.math.BigDecimal;
import java.util.List;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.Status;
import com.project.entities.User;

public class CommandListDTO {

	private long id;
	private long user;
	private List<CommandLineDTO> commandLines;
	private Status status;
	private BigDecimal totalPrice;
	
	
	public CommandListDTO() {
		super();
	}


	public CommandListDTO(long id, long user, List<CommandLineDTO> commandLines, Status status, BigDecimal totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.commandLines = commandLines;
		this.status = status;
		this.totalPrice = totalPrice;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUser() {
		return user;
	}


	public void setUser(long user) {
		this.user = user;
	}


	public List<CommandLineDTO> getCommandLines() {
		return commandLines;
	}


	public void setCommandLines(List<CommandLineDTO> commandLines) {
		this.commandLines = commandLines;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
