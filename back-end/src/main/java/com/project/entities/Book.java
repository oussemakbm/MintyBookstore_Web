package com.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Author author;
	@ManyToOne
	private Langue language;
	@ManyToOne
	private Serie serie;

	private long quantity;
	private long nbrPages;
	private long rating ; 
	private String title;
	private String description;
	private String imageUrl;
	private String publishDate;
	private float prix;
	public Book() {
		
	}
	
	
	

}
