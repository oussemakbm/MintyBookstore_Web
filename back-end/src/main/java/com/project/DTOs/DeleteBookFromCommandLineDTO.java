package com.project.DTOs;

public class DeleteBookFromCommandLineDTO {

	private long idCommandLine;
	private long idBook;
	
	
	
	public DeleteBookFromCommandLineDTO() {
		super();
	}



	public DeleteBookFromCommandLineDTO(long idBook, long idCommandLine) {
		super();
		this.idBook = idBook;
		this.idCommandLine = idCommandLine;
	}



	public long getIdBook() {
		return idBook;
	}



	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}



	public long getIdCommandLine() {
		return idCommandLine;
	}



	public void setIdCommandLine(long idCommandLine) {
		this.idCommandLine = idCommandLine;
	}
	
	
	
	
}
