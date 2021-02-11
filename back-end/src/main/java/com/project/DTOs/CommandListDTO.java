package com.project.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.project.DTOs.CommandLineDTO;
import com.project.entities.Status;
import com.project.entities.User;

public class CommandListDTO {

	private long id;
	private long userID;
	private CommandLineDTO commandLine;
	private Status status;
	private BigDecimal totalPrice;
	private LocalDateTime createdDate;
	private LocalDateTime savedDate;
	
	public CommandListDTO() {
		super();
	}


	public CommandListDTO(long id, long userID, CommandLineDTO commandLine, Status status, BigDecimal totalPrice) {
		super();
		this.id = id;
		this.userID = userID;
		this.commandLine = commandLine;
		this.status = status;
		this.totalPrice = totalPrice;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserID() {
		return userID;
	}


	public void setUserID(long userID) {
		this.userID = userID;
	}


	public CommandLineDTO getCommandLine() {
		return commandLine;
	}


	public void setCommandLines(CommandLineDTO commandLine) {
		this.commandLine = commandLine;
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


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getSavedDate() {
		return savedDate;
	}


	public void setSavedDate(LocalDateTime savedDate) {
		this.savedDate = savedDate;
	}


	public void setCommandLine(CommandLineDTO commandLine) {
		this.commandLine = commandLine;
	}
	
}
