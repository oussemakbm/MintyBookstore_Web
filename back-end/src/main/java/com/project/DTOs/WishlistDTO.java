package com.project.DTOs;

import java.util.List;

public class WishlistDTO {
	private long id;
	private List<BookDTO> books;
	private String name;
	
	
	public WishlistDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<BookDTO> getBooks() {
		return books;
	}
	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
