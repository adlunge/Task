package com.jbk.api.model;

public class Author {
	
	private int authorId;
	private String authorName;
	private String authorCountry;
	private String authorAddress;
	private String authorPhone;
	
	public Author() {
		super();
	}

	public Author(int authorId, String authorName, String authorCountry, String authorAddress, String authorPhone) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorCountry = authorCountry;
		this.authorAddress = authorAddress;
		this.authorPhone = authorPhone;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorCountry() {
		return authorCountry;
	}

	public void setAuthorCountry(String authorCountry) {
		this.authorCountry = authorCountry;
	}

	public String getAuthorAddress() {
		return authorAddress;
	}

	public void setAuthorAddress(String authorAddress) {
		this.authorAddress = authorAddress;
	}

	public String getAuthorPhone() {
		return authorPhone;
	}

	public void setAuthorPhone(String authorPhone) {
		this.authorPhone = authorPhone;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", authorCountry=" + authorCountry
				+ ", authorAddress=" + authorAddress + ", authorPhone=" + authorPhone + "]";
	}
	
}
