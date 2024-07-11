package com.lorian.models;

import java.util.Objects;

public class Order {

	private Long id;
	private Long user_id;
	private Long book_id;
	private int sold;
	
	public Order(Long id, Long user_id, Long book_id, int sold) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.sold = sold;
	}

	public Long getId() {
		return id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public Long getBook_id() {
		return book_id;
	}

	public int getSold() {
		return sold;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user_id=" + user_id + ", book_id=" + book_id + ", sold=" + sold + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book_id, id, sold, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(book_id, other.book_id) && Objects.equals(id, other.id) && sold == other.sold
				&& Objects.equals(user_id, other.user_id);
	}
	
	
}
