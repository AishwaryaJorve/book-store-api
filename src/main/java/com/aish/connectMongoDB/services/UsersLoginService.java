package com.aish.connectMongoDB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aish.connectMongoDB.model.Users;
import com.aish.connectMongoDB.repositories.UserRepository;

@Service
public class UsersLoginService {

	@Autowired
	private UserRepository userRepository;
	
	public Users signIn(String userName, String password) {
		Users user=this.userRepository.findByUserNameAndPassword(userName, password);
		return user;
	}
	
}
