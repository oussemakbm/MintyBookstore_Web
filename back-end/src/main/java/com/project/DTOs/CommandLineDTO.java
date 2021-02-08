package com.project.DTOs;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CommandLineDTO {
	
	private long userId ; 
	private long commandListId;
	private long bookId;
	private int quantity;
	private BigDecimal price;

	public CommandLineDTO() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCommandListId() {
		return commandListId;
	}

	public void setCommandListId(long commandListid) {
		this.commandListId = commandListId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookid) {
		this.bookId = bookid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}