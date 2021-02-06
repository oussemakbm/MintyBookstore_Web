package com.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "books")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Author author;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Langue language;
	
	@ManyToOne
	private Serie serie;
	
	@ManyToMany
	private List<Wishlist> wishlists;
	
	@OneToMany(mappedBy = "book")
	private List<Interaction> interactions;

	@Column(nullable = false)
	private long quantity;
	
	@Column(nullable=false)
	private long nbrPages;
	
	@Column(nullable=false)
	private long rating;
	
	@Column(unique=true, nullable=false, length = 255)
	private String title;
	
	@Column(nullable = false, columnDefinition="TEXT")
	private String description;
	
	@Column(nullable = false , length=255)
	private String imageUrl;
	
	@Column(nullable = false)
	private String publishDate;
	
	@Column(nullable = false)
	private float prix;
	
	@Transient
	private boolean available;
	
	public Book() {
		super();
	}

	public Book(Category category, Author author, Langue language, Serie serie, long quantity, long nbrPages,
			long rating, String title, String description, String imageUrl, String publishDate, float prix,
			boolean available) {
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
		this.available = available;
	}



	public Book(long id, Category category, Author author, Langue language, Serie serie, long quantity, long nbrPages,
			long rating, String title, String description, String imageUrl, String publishDate, float prix,
			boolean available) {
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
		this.available = available;
	}



	public boolean isAvailable() {
		return (quantity > 0)? true :false;
	}

	public void setAvailable(boolean available) {
		this.available = available;
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

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<Interaction> getInteractions() {
		return interactions;
	}

	public void setInteractions(List<Interaction> interactions) {
		this.interactions = interactions;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", category=" + category + ", author=" + author + ", language=" + language
				+ ", serie=" + serie + ", wishlists=" + wishlists + ", interactions=" + interactions + ", quantity="
				+ quantity + ", nbrPages=" + nbrPages + ", rating=" + rating + ", title=" + title + ", description="
				+ description + ", imageUrl=" + imageUrl + ", publishDate=" + publishDate + ", prix=" + prix
				+ ", available=" + available + "]";
	}
	

}
