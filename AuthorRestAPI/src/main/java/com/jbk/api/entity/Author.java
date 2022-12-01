package com.jbk.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
	
	@Id
	private int AuthorId;
	private String AuthorName;
	private String AuthorCountry;
	private String AuthorAddress;
	private String AuthorPhone;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(int authorId, String authorName, String authorCountry, String authorAddress, String authorPhone) {
		super();
		AuthorId = authorId;
		AuthorName = authorName;
		AuthorCountry = authorCountry;
		AuthorAddress = authorAddress;
		AuthorPhone = authorPhone;
	}

	public int getAuthorId() {
		return AuthorId;
	}

	public void setAuthorId(int authorId) {
		AuthorId = authorId;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}

	public String getAuthorCountry() {
		return AuthorCountry;
	}

	public void setAuthorCountry(String authorCountry) {
		AuthorCountry = authorCountry;
	}

	public String getAuthorAddress() {
		return AuthorAddress;
	}

	public void setAuthorAddress(String authorAddress) {
		AuthorAddress = authorAddress;
	}

	public String getAuthorPhone() {
		return AuthorPhone;
	}

	public void setAuthorPhone(String authorPhone) {
		AuthorPhone = authorPhone;
	}

	@Override
	public String toString() {
		return "Author [AuthorId=" + AuthorId + ", AuthorName=" + AuthorName + ", AuthorCountry=" + AuthorCountry
				+ ", AuthorAddress=" + AuthorAddress + ", AuthorPhone=" + AuthorPhone + "]";
	}

}
