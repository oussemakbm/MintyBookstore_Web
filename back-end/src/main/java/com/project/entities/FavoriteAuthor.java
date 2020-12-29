package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="favoriteAuthors")
public class FavoriteAuthor implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idUser;
	private long idAuthor;
	
	public FavoriteAuthor() {
		super();
	}
	
	public FavoriteAuthor(long idUser, long idAuthor) {
		super();
		this.idUser = idUser;
		this.idAuthor = idAuthor;
	}

	public FavoriteAuthor(long id, long idUser, long idAuthor) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idAuthor = idAuthor;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(long idAuthor) {
		this.idAuthor = idAuthor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idAuthor ^ (idAuthor >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
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
		FavoriteAuthor other = (FavoriteAuthor) obj;
		if (id != other.id)
			return false;
		if (idAuthor != other.idAuthor)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
}
