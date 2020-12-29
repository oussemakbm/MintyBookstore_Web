package com.project.entities;

import java.io.Serializable;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idCategory;
	private long idAuthor;
	private long idLanguage;
	private long quantity;
	private long nbrPages;
	private long rating ; 
	private long idSerie;
	private String title;
	private String description;
	private String imageUrl;
	private String publishDate;
	private float prix;
	public Book() {
		
	}
	
	
	
	public Book(long idCategory, long idAuthor, long idLanguage, long quantity, long nbrPages, long rating,
			long idSerie, String title, String description, String imageUrl, String publishDate, float prix) {
		super();
		this.idCategory = idCategory;
		this.idAuthor = idAuthor;
		this.idLanguage = idLanguage;
		this.quantity = quantity;
		this.nbrPages = nbrPages;
		this.rating = rating;
		this.idSerie = idSerie;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.prix = prix;
	}



	public Book(long id, long idCategory, long idAuthor, long idLanguage, long quantity, long nbrPages, long rating,
			long idSerie, String title, String description, String imageUrl, String publishDate, float prix) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.idAuthor = idAuthor;
		this.idLanguage = idLanguage;
		this.quantity = quantity;
		this.nbrPages = nbrPages;
		this.rating = rating;
		this.idSerie = idSerie;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.prix = prix;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
	public long getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(long idAuthor) {
		this.idAuthor = idAuthor;
	}
	public long getIdLanguage() {
		return idLanguage;
	}
	public void setIdLanguage(long idLanguage) {
		this.idLanguage = idLanguage;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getNbrPages() {
		return nbrPages;
	}
	public void setNbrPages(long nbrPages) {
		this.nbrPages = nbrPages;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public long getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(long idSerie) {
		this.idSerie = idSerie;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", idCategory=" + idCategory + ", idAuthor=" + idAuthor + ", idLanguage=" + idLanguage
				+ ", quantity=" + quantity + ", nbrPages=" + nbrPages + ", rating=" + rating + ", idSerie=" + idSerie
				+ ", title=" + title + ", description=" + description + ", imageUrl=" + imageUrl + ", publishDate="
				+ publishDate + ", prix=" + prix + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idAuthor ^ (idAuthor >>> 32));
		result = prime * result + (int) (idCategory ^ (idCategory >>> 32));
		result = prime * result + (int) (idLanguage ^ (idLanguage >>> 32));
		result = prime * result + (int) (idSerie ^ (idSerie >>> 32));
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + (int) (nbrPages ^ (nbrPages >>> 32));
		result = prime * result + Float.floatToIntBits(prix);
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		result = prime * result + (int) (rating ^ (rating >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (idAuthor != other.idAuthor)
			return false;
		if (idCategory != other.idCategory)
			return false;
		if (idLanguage != other.idLanguage)
			return false;
		if (idSerie != other.idSerie)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (nbrPages != other.nbrPages)
			return false;
		if (Float.floatToIntBits(prix) != Float.floatToIntBits(other.prix))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (quantity != other.quantity)
			return false;
		if (rating != other.rating)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
	
	
    
	
	

}
