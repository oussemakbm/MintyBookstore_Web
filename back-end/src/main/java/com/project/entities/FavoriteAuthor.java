package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="favoriteAuthors")
public class FavoriteAuthor implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
}
