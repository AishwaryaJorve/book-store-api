package com.aish.connectMongoDB.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aish.connectMongoDB.model.Book;
import com.aish.connectMongoDB.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String>{

	public Users findByUserNameAndPassword(String userName,String password);
	
}
