package com.jbk.api.dao;

import com.jbk.api.entity.Author;

public interface AuthorDao {

	public boolean saveAuthor(Author author);
	public Author getAuthorById(int authorId);
}
