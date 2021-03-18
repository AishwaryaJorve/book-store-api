package com.aish.connectMongoDB.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aish.connectMongoDB.jwtUtil.JwtUtil;
import com.aish.connectMongoDB.model.Book;
import com.aish.connectMongoDB.model.Users;
import com.aish.connectMongoDB.repositories.UserRepository;
import com.aish.connectMongoDB.services.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
	private BookService bookService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

	@GetMapping("/findAllBooks/{id}")
	public ResponseEntity<List<Book>> getBooks(@PathVariable("id") String id) {
		System.out.println(id);
		//find user by id
		Users user=this.userRepository.findById(id).get();
		
		//find all books of that particular user
		List<Book> books=user.getBooks(); 
		
		return ResponseEntity.ok(books);
	}

	@PutMapping("/updateBook/{id}")
	public ResponseEntity<String> updateBook(@PathVariable("id") String id, @RequestBody Users userToBeUpdate) throws Exception {
		
		//find user by userId
		Users user=this.userRepository.findById(id).get();
		
		//set books in user getting from userToBeUpdate
		user.setBooks(userToBeUpdate.getBooks());
		
		userRepository.save(user);
		return ResponseEntity.ok("Updated book");
	}
	
	
//	@GetMapping("/findByBookName/{bookName}")
//	public List<Book> getBookByName(@PathVariable String bookName) {
//		return bookService.getBookByName(bookName);
//	}
	
	
//
//	@GetMapping("/findBookById/{id}")
//	public Optional<Book> getBook(@PathVariable("id") String id) {
//		return bookService.getBook(id);
//	}
//
//	




}
