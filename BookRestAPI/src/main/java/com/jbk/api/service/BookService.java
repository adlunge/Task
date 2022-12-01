package com.jbk.api.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Book;

import com.jbk.api.model.BookAuthor;

public interface BookService {

	public boolean saveBook(Book book);

	public Book getBookById(int bookId);

	public List<Book> getAllBooks();

	public boolean deleteBook(int bookId);

	public boolean updateBook(Book book);

	public List<Book> getMaxPriceBooks();

	public List<Book> sortBooksById_ASC();

	public List<Book> sortBooksByName_DESC();

	public double countSumOfBookPrice();

	public long getTotalCountOfBooks();

	public String uploadSheet(MultipartFile file, HttpSession session);
	
	public BookAuthor get_book_with_author_by_id(int bookId);

}
