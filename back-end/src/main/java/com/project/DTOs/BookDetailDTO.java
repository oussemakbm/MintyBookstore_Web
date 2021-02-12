package com.project.DTOs;

import java.util.List;

import com.project.entities.Interaction;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookDetailDTO {
	
	private long id;
	
	private CategoryDTO category;
	
	private AuthorDTO author;
	
	private LangueDTO language;
	
	private SerieDTO serie;
		
	private long quantity;
	
	private long nbrPages;
	
	private long rating;
	
	private String title;
	
	private String description;
	
	private String imageUrl;
	
	private String publishDate;
	
	private float prix;
	
	private boolean available;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public LangueDTO getLanguage() {
		return language;
	}

	public void setLanguage(LangueDTO language) {
		this.language = language;
	}

	public SerieDTO getSerie() {
		return serie;
	}

	public void setSerie(SerieDTO serie) {
		this.serie = serie;
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

	public boolean isAvailable() {
		return (quantity > 0)? true : false;
	}

	public void setAvailable() {
		this.available = (quantity > 0)? true : false;
	}
	
}
