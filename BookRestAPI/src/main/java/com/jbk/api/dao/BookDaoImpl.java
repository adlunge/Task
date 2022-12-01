package com.jbk.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jbk.api.entity.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveBook(Book book) {
		// get session factory

		boolean isAdded = false;
		Session session = sessionFactory.openSession();
		try {
			Book bok = session.get(Book.class, book.getBookId());
			if (bok == null) {
				Transaction transaction = session.beginTransaction();
				session.save(book);
				transaction.commit();
				isAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isAdded;

	}

	@Override
	public Book getBookById(int bookId) {
		Session session = sessionFactory.openSession();
		Book book = null;
		try {
			book = session.get(Book.class, bookId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		Session session = sessionFactory.openSession();
		List<Book> list = null;
		try {
			Criteria criteria = session.createCriteria(Book.class);

			criteria.addOrder(Order.asc("bookName"));

			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public boolean deleteBook(int bookId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isDeleted = false;
		try {
			Book book = session.get(Book.class, bookId);
			if (book != null) {
				session.delete(book);
				transaction.commit();
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isDeleted;
	}

	@Override
	public boolean updateBook(Book book) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated = false;
		try {
			Book bok = session.get(Book.class, book.getBookId());
			session.evict(bok);
			if (bok != null) {
				session.update(book);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public List<Book> getMaxPriceBooks() {
		Session session = sessionFactory.openSession();
		List<Book> maxBooks = null;
		double maxPrice = 0;
		try {
			Criteria maxcriteria = session.createCriteria(Book.class);
			Criteria eqcriteria = session.createCriteria(Book.class);
			maxcriteria.setProjection(Projections.max("bookPrice"));
			List<Double> list = maxcriteria.list();
			maxPrice = list.get(0);

			eqcriteria.add(Restrictions.eq("bookPrice", maxPrice));
			maxBooks = eqcriteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return maxBooks;
	}

	@Override
	public List<Book> sortBooksById_ASC() {
		Session session = sessionFactory.openSession();
		List<Book> sortedList = null;

		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.asc("bookId"));
			sortedList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return sortedList;
	}

	@Override
	public List<Book> sortBooksByName_DESC() {
		Session session = sessionFactory.openSession();
		List<Book> sortedList = null;

		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.desc("bookName"));
			sortedList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return sortedList;
	}

	@Override
	public double countSumOfBookPrice() {
		Session session = sessionFactory.openSession();
		double sumOfBooksPrice = 0;

		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.setProjection(Projections.sum("bookPrice"));

			List<Double> list = criteria.list();
			if (!list.isEmpty()) {
				sumOfBooksPrice = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return sumOfBooksPrice;
	}

	@Override
	public long getTotalCountOfBooks() {
		Session session = sessionFactory.openSession();
		long countOfBooksPrice = 0;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.setProjection(Projections.rowCount());
			List<Long> list = criteria.list();
			if (!list.isEmpty()) {
				countOfBooksPrice = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return countOfBooksPrice;
	}

	@Override
	public int excelToDb(List<Book> list) {
		int addedCount = 0;
		for (Book book : list) {
			boolean isAdded = saveBook(book);
			if (isAdded) {
				addedCount = addedCount + 1;
			}
		}
		return addedCount;
	}
}
