package com.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="interactions")
@Entity
public class Interaction implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User users;
	@ManyToOne
	private Book book;
	
	private double ratingValue;
	
	private boolean liked;

	public Interaction() {
		super();
	}

	public Interaction(long id, User users, Book book, double ratingValue, boolean liked) {
		super();
		this.id = id;
		this.users = users;
		this.book = book;
		this.ratingValue = ratingValue;
		this.liked = liked;
	}

	public Interaction(User users, Book book, double ratingValue, boolean liked) {
		super();
		this.users = users;
		this.book = book;
		this.ratingValue = ratingValue;
		this.liked = liked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public double getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (liked ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(ratingValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Interaction other = (Interaction) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (id != other.id)
			return false;
		if (liked != other.liked)
			return false;
		if (Double.doubleToLongBits(ratingValue) != Double.doubleToLongBits(other.ratingValue))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interaction [id=" + id + ", users=" + users + ", book=" + book + ", ratingValue=" + ratingValue
				+ ", liked=" + liked + "]";
	}
	

		
	
	
	
}
