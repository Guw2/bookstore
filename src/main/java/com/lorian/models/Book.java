package com.lorian.models;

import java.util.Objects;

public class Book {

	private Long id;
	private String title;
	private String description;
	private Float price;
	private Long stock;
	private String image;
	private String author;
	

	public Book(Long id, String title, String description, Float price, Long stock, String image, String author) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.author = author;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getAuthor() {
		return author;
	}
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Float getPrice() {
		return price;
	}

	public Long getStock() {
		return stock;
	}
	
	public String getImage() {
		return image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, price, stock, title);
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
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && Objects.equals(stock, other.stock)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price + ", stock="
				+ stock + "]";
	}
	
	
}
