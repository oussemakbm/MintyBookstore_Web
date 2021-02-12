package com.project.DTOs;


import lombok.Data;

@Data
public class SuggestDTO {
	private BookDTO book;
	private int nbUsers;
	
	public SuggestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookDTO getBook() {
		return book;
	}
	public void setBook(BookDTO book) {
		this.book = book;
	}
	public int getNbUsers() {
		return nbUsers;
	}
	public void setNbUsers(int nbUsers) {
		this.nbUsers = nbUsers;
	}
	
	
}
