package com.jbk.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.api.dao.AuthorDao;
import com.jbk.api.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorDao dao;

	@Override
	public boolean saveAuthor(Author author) {
		
		return dao.saveAuthor(author);
	}

	@Override
	public Author getAuthorById(int authorId) {
		
		return dao.getAuthorById(authorId);
	}

}
