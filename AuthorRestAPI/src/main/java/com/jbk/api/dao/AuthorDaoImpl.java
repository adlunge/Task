package com.jbk.api.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.api.entity.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveAuthor(Author author) {

		boolean isAdded = false;
		Session session = sessionFactory.openSession();
		try {
			Author dbAuthor = session.get(Author.class, author.getAuthorId());
			if (dbAuthor == null) {
				Transaction transaction = session.beginTransaction();
				session.save(author);
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
	public Author getAuthorById(int authorId) {
		Session session = sessionFactory.openSession();
		Author author = null;
		try {
			author = session.get(Author.class, authorId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return author;
	}

}
