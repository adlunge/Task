package com.jbk.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.dao.BookDao;
import com.jbk.api.entity.Book;
import com.jbk.api.model.BookAuthor;
import com.jbk.api.model.Author;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao dao;

	@Autowired
	private RestTemplate restTemplate;

	int totalBookInSheet = 0;

	@Override

	public boolean saveBook(Book book) {

		boolean isAdded = dao.saveBook(book);

		return isAdded;
	}

	@Override
	public Book getBookById(int bookId) {

		return dao.getBookById(bookId);
	}

	@Override
	public List<Book> getAllBooks() {

		return dao.getAllBooks();
	}

	@Override
	public boolean deleteBook(int bookId) {

		return dao.deleteBook(bookId);
	}

	@Override
	public boolean updateBook(Book book) {

		return dao.updateBook(book);
	}

	@Override
	public List<Book> getMaxPriceBooks() {

		return dao.getMaxPriceBooks();
	}

	@Override
	public List<Book> sortBooksById_ASC() {

		return dao.sortBooksById_ASC();
	}

	@Override
	public List<Book> sortBooksByName_DESC() {

		return dao.sortBooksByName_DESC();
	}

	@Override
	public double countSumOfBookPrice() {

		return dao.countSumOfBookPrice();
	}

	@Override
	public long getTotalCountOfBooks() {

		return dao.getTotalCountOfBooks();
	}

	public List<Book> readExcel(String path) {
		Book book = null;
		List<Book> list = new ArrayList<>();

		try {

			FileInputStream fis = new FileInputStream(new File(path));

			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			totalBookInSheet = sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();
			int cnt = 0;

			while (rows.hasNext()) {
				Row row = rows.next();
				book = new Book();
				if (cnt == 0) {
					cnt = cnt + 1;
					continue;
				}

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {

					Cell cell = cells.next();
					int col = cell.getColumnIndex();

					switch (col) {
					case 0: {
						book.setBookId((int) cell.getNumericCellValue());

						break;
					}

					case 1: {
						book.setBookName(cell.getStringCellValue());
						break;
					}

					case 2: {
						book.setBookQty((int) cell.getNumericCellValue());
						break;
					}

					case 3: {
						book.setBookPrice(cell.getNumericCellValue());
						break;
					}

					case 4: {
						book.setBookType(cell.getStringCellValue());
						break;
					}

					default:
						break;
					}

				}

				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public String uploadSheet(MultipartFile file, HttpSession session) {
		int addedCount = 0;
		String path = session.getServletContext().getRealPath("/uploaded");
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);

		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(path + File.separator + fileName));
			fos.write(data);

			List<Book> list = readExcel(path + File.separator + fileName);

			addedCount = dao.excelToDb(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Total book in sheet =" + totalBookInSheet + " and Added Book count =" + addedCount;
	}

	@Override
	public BookAuthor get_book_with_author_by_id(int bookId) {
		BookAuthor bookAuthor = new BookAuthor();
		Book book = getBookById(bookId);
		bookAuthor.setBook(book);
		System.out.println(book);
		Author author= restTemplate.getForObject("http://localhost:8082/author/get_author_by_id/1", Author.class);
		bookAuthor.setAuthor(author);
		return bookAuthor;
	}

}
