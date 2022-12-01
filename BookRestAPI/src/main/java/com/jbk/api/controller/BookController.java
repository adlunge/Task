package com.jbk.api.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Book;
import com.jbk.api.exception.BookAlreadyExistsException;
import com.jbk.api.model.BookAuthor;
import com.jbk.api.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping(value = "/save_book")
	public ResponseEntity<Boolean> saveBook(@Valid @RequestBody Book book) {

		
		
		boolean isAdded = service.saveBook(book);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			throw new BookAlreadyExistsException("Book Already Exists ID=>>"+book.getBookId());
		}

	}

	@GetMapping(value = "/get_book_by_id/{bookid}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookid) {
		Book book = service.getBookById(bookid);
		if (book != null) {
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<Book>(HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/get_book_with_author_by_id/{bookId}")
	public ResponseEntity<BookAuthor> get_book_with_author_by_id(@PathVariable int bookId){
		BookAuthor bookAuthor = service.get_book_with_author_by_id(bookId);
		return new ResponseEntity<BookAuthor>(bookAuthor, HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = service.getAllBooks();
		if (!allBooks.isEmpty()) {
			return new ResponseEntity<List<Book>>(allBooks, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/delete_book")
	public ResponseEntity<Boolean> getAllBooks(@RequestParam int bookid) {
		boolean isDeleted = service.deleteBook(bookid);
		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
	}

	@PutMapping(value = "/update_book")
	public ResponseEntity<Boolean> updateBook(@Valid @RequestBody Book book) {
		boolean isUpdated = service.updateBook(book);
		return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
	}

	@GetMapping(value = "/sort_books_by_id_asc")
	public ResponseEntity<List<Book>> sortBooksById_ASC() {
		List<Book> sortedList = service.sortBooksById_ASC();
		if (!sortedList.isEmpty()) {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(HttpStatus.OK);
		}

	} 
	
	@GetMapping(value = "/sort_books_by_name_DESC")
	public ResponseEntity<List<Book>> sortBooksByName_DESC() {
		List<Book> sortedList = service.sortBooksByName_DESC();
		if (!sortedList.isEmpty()) {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(HttpStatus.OK);
		}

	} 
	
	@GetMapping(value = "/get_max_price_books")
	public ResponseEntity<List<Book>> getMaxPriceBooks() {
		List<Book> maxPriceBooks = service.getMaxPriceBooks();
		return new ResponseEntity<List<Book>>(maxPriceBooks, HttpStatus.OK);

	}
	
	@GetMapping(value = "/count_sum_of_books_price")
	public ResponseEntity<Double> countSumOfBookPrice() {
		double sumOfBooks = service.countSumOfBookPrice();
		return new ResponseEntity<Double>(sumOfBooks, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get_total_count_of_books")
	public ResponseEntity<Long> getTotalCountOfBooks() {
		long countOfBooks = service.getTotalCountOfBooks();
		return new ResponseEntity<Long>(countOfBooks, HttpStatus.OK);
	}
	
	@PostMapping(value = "/uploadsheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file,HttpSession session){	
	String msg = service.uploadSheet(file, session);
	return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
