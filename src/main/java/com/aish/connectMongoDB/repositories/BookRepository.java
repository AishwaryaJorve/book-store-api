package com.aish.connectMongoDB.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aish.connectMongoDB.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{
	public List<Book> findByBookName(String bookName);
	public Optional <Book> findByBookId(String bookId);
}


