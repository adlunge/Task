package com.jbk.api.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

public class Book {

	@Id
	@Min(1)
	private int bookId;
	
	@NotNull(message = "Book name is required")
	private String bookName;
	
	@Min(1)
	private double bookPrice;
	
	@Min(1)
	private int bookQty;
	
	@NotNull(message = "Book type is required")
	private String bookType;
	
	private int authorId;

	public Book() {
		super();
	}

	public Book(@Min(1) int bookId, @NotNull(message = "Book name is required") String bookName,
			@Min(1) double bookPrice, @Min(1) int bookQty, @NotNull(message = "Book type is required") String bookType,
			int authorId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookQty = bookQty;
		this.bookType = bookType;
		this.authorId = authorId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookQty() {
		return bookQty;
	}

	public void setBookQty(int bookQty) {
		this.bookQty = bookQty;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", bookQty=" + bookQty
				+ ", bookType=" + bookType + ", authorId=" + authorId + "]";
	}
	
}
