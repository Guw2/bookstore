package com.lorian.models;

import java.util.Objects;

public class Comment {

	private Long id;
	private String user;
	private Long likes;
	private String content;
	private Long book_id;
	
	public Comment(Long id, String user, Long likes, String content, Long book_id) {
		super();
		this.id = id;
		this.user = user;
		this.likes = likes;
		this.content = content;
		this.book_id = book_id;
	}

	public Long getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public Long getLikes() {
		return likes;
	}

	public String getContent() {
		return content;
	}

	public Long getBook_id() {
		return book_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user=" + user + ", likes=" + likes + ", content=" + content + ", book_id="
				+ book_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book_id, content, id, likes, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(book_id, other.book_id) && Objects.equals(content, other.content)
				&& Objects.equals(id, other.id) && Objects.equals(likes, other.likes)
				&& Objects.equals(user, other.user);
	}
	
	
	
}
