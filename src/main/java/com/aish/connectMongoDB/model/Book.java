package com.aish.connectMongoDB.model;




//import javax.annotation.processing.Generated;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.Data;


/*import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString*/

@Document(collection = "Book")
public class Book {
	@Id
	private String bookId;
	private String bookName;
	private String authorName;
	private String discription;
	
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId, String bookName, String authorName, String discription) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.discription = discription;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getBookId() {
		return bookId;
	}
	public void setId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", discription="
				+ discription + "]";
	}
	
	
	
	
	
	
}
