package com.project.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="authors")
public class Author implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique=true)
	private String name;
	private String description;
	private String picUrl;
	
	@OneToMany
	private List<Book> books;
	
	public Author() {
		super();
	}
	
	public Author(String name, String description, String picUrl) {
		super();
		name = name;
		description = description;
		picUrl = picUrl;
	}

	public Author(long id, String name, String description, String picUrl) {
		super();
		this.id = id;
		name = name;
		description = description;
		picUrl = picUrl;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

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
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return this.picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.picUrl == null) ? 0 : this.picUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Author other = (Author) obj;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		if (this.picUrl == null) {
			if (other.picUrl != null)
				return false;
		} else if (!this.picUrl.equals(other.picUrl))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", Name=" + name + ", Description=" + description + ", PicUrl=" + picUrl + "]";
	}
	
}
