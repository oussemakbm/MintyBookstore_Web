package com.project.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandLines")
public class CommandLine implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idCommandList;
	private long idBook;
	private long quantity;
	
	public CommandLine() {
		super();
	}
	
	public CommandLine(long id, long idCommandList, long idBook, long quantity) {
		super();
		this.id = id;
		this.idCommandList=idCommandList;
		this.idBook=idBook;
		this.quantity=quantity;
	}
	

	public CommandLine(long idCommandList, long idBook, long quantity) {
		super();
		this.idCommandList=idCommandList;
		this.idBook=idBook;
		this.quantity=quantity;
	}



	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdCommandList() {
		return idCommandList;
	}

	public void setIdCommandList(long idCommandList) {
		this.idCommandList = idCommandList;
	}

	public long getIdBook() {
		return idBook;
	}

	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idBook ^ (idBook >>> 32));
		result = prime * result + (int) (idCommandList ^ (idCommandList >>> 32));
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandLine other = (CommandLine) obj;
		if (id != other.id)
			return false;
		if (idBook != other.idBook)
			return false;
		if (idCommandList != other.idCommandList)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandLine [id=" + id + ", idCommandList=" + idCommandList + ", idBook=" + idBook + ", quantity="
				+ quantity + "]";
	}
	
	
	
	
}
