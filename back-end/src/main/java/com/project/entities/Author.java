package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="authors")
public class Author implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	private String Name;
	private String Description;
	private String PicUrl;
	
	public Author() {
		super();
	}
	
	
	public Author(String name, String description, String picUrl) {
		super();
		Name = name;
		Description = description;
		PicUrl = picUrl;
	}


	public Author(long id, String name, String description, String picUrl) {
		super();
		this.id = id;
		Name = name;
		Description = description;
		PicUrl = picUrl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((PicUrl == null) ? 0 : PicUrl.hashCode());
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
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (PicUrl == null) {
			if (other.PicUrl != null)
				return false;
		} else if (!PicUrl.equals(other.PicUrl))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Author [id=" + id + ", Name=" + Name + ", Description=" + Description + ", PicUrl=" + PicUrl + "]";
	}
	
}
