package com.aish.connectMongoDB.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aish.connectMongoDB.model.Book;
import com.aish.connectMongoDB.repositories.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public String saveBook(@RequestBody Book book) {
		this.bookRepository.save(book);
		return "Added book with bookId : " + book.getBookId();
	}

	public List<Book> getBooks() {
		List<Book> books= this.bookRepository.findAll();
		return books;
	}

	public List<Book> getBookByName(String bookName) {
		List<Book> booksByName =this.bookRepository.findByBookName(bookName);
		return booksByName;
	}

	public Optional<Book> getBook(String bookId) {
		Optional<Book> bookById= this.bookRepository.findByBookId(bookId);
		return bookById;
	}

	public String deleteBook(String bookId) {
		this.bookRepository.deleteById(bookId);
		return "book deleted with id : " + bookId;
	}
	
	public Book updateBook(String bookId, Book book) throws Exception {
		Book UpdatedBook = this.bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
		
		UpdatedBook.setAuthorName(book.getAuthorName());
		UpdatedBook.setDiscription(book.getDiscription());
		return this.bookRepository.save(UpdatedBook);
	}
}
