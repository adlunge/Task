package com.jbk.api.service;

import com.jbk.api.entity.Author;

public interface AuthorService {
	
	public boolean saveAuthor(Author author);
	public Author getAuthorById(int authorId);
	

}
