package com.jbk.api.dao;

import java.util.List;

import com.jbk.api.entity.Book;

public interface BookDao {
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
	
	public int excelToDb(List<Book> list);

}
