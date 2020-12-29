package com.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name="interactions")
@Entity
public class Interaction implements Serializable{
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private long idUser;
	
	private long idBook;
	
	private double ratingValue;
	
	private boolean liked;

	public Interaction() {
		super();
	}

	public Interaction(long id, long idUser, long idBook, double ratingValue, boolean liked) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idBook = idBook;
		this.ratingValue = ratingValue;
		this.liked = liked;
	}
	
	
	
	
	
}
