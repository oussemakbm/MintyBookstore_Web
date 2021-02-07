package com.project.DTOs;

public class AddBookToCategoryDTO {
	
	private long idBook,idCategory;

	public AddBookToCategoryDTO() {
		super();
	}

	public AddBookToCategoryDTO(long idBook, long idCategory) {
		super();
		this.idBook = idBook;
		this.idCategory = idCategory;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
	
	

}
