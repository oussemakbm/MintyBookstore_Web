package com.project.DTOs;

public class InteractionDTO {
	
	
	private double ratingValue;
	
	private boolean liked;

	public double getRatingValue() {
		return ratingValue;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	
	
}
