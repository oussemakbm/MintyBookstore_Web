package com.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="wishlists")
@Entity
public class Wishlist implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private long idUser;
	private long idBook;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdBook() {
		return idBook;
	}
	public void setIdBook(long idBook) {
		this.idBook = idBook;
	}
	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", name=" + name + ", idUser=" + idUser + ", idBook=" + idBook + "]";
	}
	public Wishlist() {
		super();
	}
	public Wishlist(long id, String name, long idUser, long idBook) {
		super();
		this.id = id;
		this.name = name;
		this.idUser = idUser;
		this.idBook = idBook;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idBook ^ (idBook >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Wishlist other = (Wishlist) obj;
		if (id != other.id)
			return false;
		if (idBook != other.idBook)
			return false;
		if (idUser != other.idUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
