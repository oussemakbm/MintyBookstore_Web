package com.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Author author;
	@ManyToOne
	private Langue language;
	@ManyToOne
	private Serie serie;

	@ManyToMany
	private List<Wishlist> wishlists;
	@OneToMany(mappedBy = "book")
	private List<Interaction> interactions;
	private long quantity;
	private long nbrPages;
	private long rating ; 
	
	private String title;
	private String description;
	private String imageUrl;
	private String publishDate;
	private float prix;
	
	public Book() {
		
	}
	
	public Book(Category category, Author author, Langue language, Serie serie, long quantity, long nbrPages,
			long rating, String title, String description, String imageUrl, String publishDate, float prix) {
		super();
		this.category = category;
		this.author = author;
		this.language = language;
		this.serie = serie;
		this.quantity = quantity;
		this.nbrPages = nbrPages;
		this.rating = rating;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.prix = prix;
	}


	public Book(long id, Category category, Author author, Langue language, Serie serie, long quantity, long nbrPages,
			long rating, String title, String description, String imageUrl, String publishDate, float prix) {
		super();
		this.id = id;
		this.category = category;
		this.author = author;
		this.language = language;
		this.serie = serie;
		this.quantity = quantity;
		this.nbrPages = nbrPages;
		this.rating = rating;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.publishDate = publishDate;
		this.prix = prix;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Langue getLanguage() {
		return language;
	}
	public void setLanguage(Langue language) {
		this.language = language;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
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

	@Override
	public String toString() {
		return "Book [id=" + id + ", category=" + category + ", author=" + author + ", language=" + language
				+ ", serie=" + serie + ", quantity=" + quantity + ", nbrPages=" + nbrPages + ", rating=" + rating
				+ ", title=" + title + ", description=" + description + ", imageUrl=" + imageUrl + ", publishDate="
				+ publishDate + ", prix=" + prix + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + (int) (nbrPages ^ (nbrPages >>> 32));
		result = prime * result + Float.floatToIntBits(prix);
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		result = prime * result + (int) (rating ^ (rating >>> 32));
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
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
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}	
}
