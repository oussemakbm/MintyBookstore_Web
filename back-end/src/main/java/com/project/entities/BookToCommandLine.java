package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookToCommandLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private CommandLine commandLine;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public CommandLine getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(CommandLine commandLine) {
		this.commandLine = commandLine;
	}
	

	

}
