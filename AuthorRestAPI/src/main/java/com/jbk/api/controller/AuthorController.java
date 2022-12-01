package com.jbk.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jbk.api.entity.Author;
import com.jbk.api.exception.AuthorAlreadyExistsException;
import com.jbk.api.service.AuthorService;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

	@Autowired
	private AuthorService service;

	@PostMapping(value = "/save_author")
	public ResponseEntity<Boolean> saveauthor(@Valid @RequestBody Author author) {

		boolean isAdded = service.saveAuthor(author);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			throw new AuthorAlreadyExistsException("Author Already Exists ID=>>" + author.getAuthorId());
		}
	}
	
	@GetMapping(value = "/get_author_by_id/{authorid}")
	public ResponseEntity<Author> getauthorById(@PathVariable int authorid) {
		Author author = service.getAuthorById(authorid);
		if (author != null) {
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		} else {
			return new ResponseEntity<Author>(HttpStatus.OK);
		}

	}

}
