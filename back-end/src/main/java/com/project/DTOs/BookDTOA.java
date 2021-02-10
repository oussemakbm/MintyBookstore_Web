package com.project.DTOs;

public class BookDTOA {
	
	private long id;
	
	private long categoryID;
	
	private long authorID;
	
	private long languageID;
	
	private long serieID;
		
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

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public long getAuthorID() {
		return authorID;
	}

	public void setAuthorID(long authorID) {
		this.authorID = authorID;
	}

	public long getLanguageID() {
		return languageID;
	}

	public void setLanguageID(long languageID) {
		this.languageID = languageID;
	}

	public long getSerieID() {
		return serieID;
	}

	public void setSerieID(long serieID) {
		this.serieID = serieID;
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
