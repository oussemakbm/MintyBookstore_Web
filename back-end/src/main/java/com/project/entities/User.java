package com.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import net.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy;


@Table(name="users")
@Entity
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
	private String password;
	private String numTel;
	private String adresse;
	private String role;
	private String picUrl;
	@OneToMany
	private List<Serie> favoriteSeries;
	@OneToMany
	private List<Author> favoriteAuthors;
	
	public User() {
		super();
	}

	public User(String username, String email, String password, String numTel, String adresse, String role, String picUrl,
			List<Serie> favoriteSeries, List<Author> favoriteAuthors) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.numTel = numTel;
		this.adresse = adresse;
		this.role = role;
		this.picUrl = picUrl;
		this.favoriteSeries = favoriteSeries;
		this.favoriteAuthors = favoriteAuthors;
	}

	public User(long id, String username, String email, String password, String numTel, String adresse, String role,
			String picUrl) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.numTel = numTel;
		this.adresse = adresse;
		this.role = role;
		this.picUrl = picUrl;
	}

	public List<Serie> getFavoriteSeries() {
		return favoriteSeries;
	}

	public void setFavoriteSeries(List<Serie> favoriteSeries) {
		this.favoriteSeries = favoriteSeries;
	}

	public List<Author> getFavoriteAuthors() {
		return favoriteAuthors;
	}

	public void setFavoriteAuthors(List<Author> favoriteAuthors) {
		this.favoriteAuthors = favoriteAuthors;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", email=" + email + ", password=" + password + ", numTel="
				+ numTel + ", adresse=" + adresse + ", role=" + role + ", picUrl=" + picUrl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((numTel == null) ? 0 : numTel.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((picUrl == null) ? 0 : picUrl.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (numTel == null) {
			if (other.numTel != null)
				return false;
		} else if (!numTel.equals(other.numTel))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (picUrl == null) {
			if (other.picUrl != null)
				return false;
		} else if (!picUrl.equals(other.picUrl))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
