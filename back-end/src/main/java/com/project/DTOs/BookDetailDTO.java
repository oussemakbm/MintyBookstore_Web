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
	
	private String category;
	
	private String author;
	
	private String language;
	
	private String serie;
		
	private List<Interaction> interactions;

	private long quantity;
	
	private long nbrPages;
	
	private long rating;
	
	private String title;
	
	private String description;
	
	private String imageUrl;
	
	private String publishDate;
	
	private float prix;
}
