package com.project.DTOs;

import lombok.Data;

@Data
public class BookDTO {
	
	private long id;
	
	private long rating;
	
	private String title;
		
	private String imageUrl;
		
	private float prix;
	
	private long quantity;
	
	private boolean available;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailable() {
		return (quantity > 0)? true : false;
	}

	public void setAvailable() {
		this.available = (quantity > 0)? true : false;
	}
	
}
