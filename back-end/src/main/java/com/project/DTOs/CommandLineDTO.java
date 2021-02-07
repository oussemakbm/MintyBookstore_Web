package com.project.DTOs;

import lombok.Data;

@Data
public class CommandLineDTO {
	
	private long userId , idCommandList;
	private long idBook;
	private int qty;
	


	public CommandLineDTO() {
		super();
	}
	


	public CommandLineDTO(long userId, long idCommandList, long idBook, int qty) {
		super();
		this.userId = userId;
		this.idCommandList = idCommandList;
		this.idBook = idBook;
		this.qty = qty;
	}



	public long getUserId() {
		return userId;
	}


	public void setUserId(long UserId) {
		this.userId = userId;
	}


	public long getIdBook() {
		return idBook;
	}


	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}



	public long getIdCommandList() {
		return idCommandList;
	}



	public void setIdCommandList(long idCommandList) {
		this.idCommandList = idCommandList;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	

}
