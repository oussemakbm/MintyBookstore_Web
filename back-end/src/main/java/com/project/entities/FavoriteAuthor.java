package com.project.entities;

import javax.persistence.*;

@Entity
@Table(name="favoriteAuthors")
public class FavoriteAuthor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
}
