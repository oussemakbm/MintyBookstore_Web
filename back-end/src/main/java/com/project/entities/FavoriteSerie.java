package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="favoriteSeries")
public class FavoriteSerie implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idUser;
	private long idSerie;
	
	
	public FavoriteSerie() {
		super();
	}
	

	public FavoriteSerie(long idUser, long idSerie) {
		super();
		this.idUser = idUser;
		this.idSerie = idSerie;
	}



	public FavoriteSerie(long id, long idUser, long idSerie) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idSerie = idSerie;
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
	public long getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(long idSerie) {
		this.idSerie = idSerie;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idSerie ^ (idSerie >>> 32));
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
		FavoriteSerie other = (FavoriteSerie) obj;
		if (id != other.id)
			return false;
		if (idSerie != other.idSerie)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FavoriteSerie [id=" + id + ", idUser=" + idUser + ", idSerie=" + idSerie + "]";
	}
	
}
