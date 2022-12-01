package com.jbk.api.model;

import com.jbk.api.entity.Book;

public class BookAuthor {
	
	private Book book;
	private Author author;
	
	public BookAuthor() {
		super();
	}

	public BookAuthor(Book book, Author author) {
		super();
		this.book = book;
		this.author = author;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookAuthor [book=" + book + ", author=" + author + "]";
	}
}
