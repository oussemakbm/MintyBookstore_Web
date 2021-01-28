package com.project.DTOs;

import lombok.Data;

@Data
public class AddBookToWishlistDTO {

	private long idWishlist,idBook;

	public AddBookToWishlistDTO(long idWishlist, long idBook) {
		super();
		this.idWishlist = idWishlist;
		this.idBook = idBook;
	}

	public AddBookToWishlistDTO() {
		super();
	}

	public long getIdWishlist() {
		return idWishlist;
	}

	public void setIdWishlist(long idWishlist) {
		this.idWishlist = idWishlist;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}
	
	
}