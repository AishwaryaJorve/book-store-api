package com.aish.connectMongoDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aish.connectMongoDB.jwtUtil.JwtUtil;
import com.aish.connectMongoDB.model.JwtRequest;
import com.aish.connectMongoDB.model.JwtTokenWithUser;
import com.aish.connectMongoDB.model.Users;
import com.aish.connectMongoDB.repositories.UserRepository;
import com.aish.connectMongoDB.services.UsersLoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UsersLoginService usersLoginService;
	
	@PostMapping("/addUser")
	public ResponseEntity<JwtTokenWithUser> addUser(@RequestBody Users userToAdd){
		
		// save new user
		Users addedUser=userRepository.save(userToAdd);
		
		//generate token using user name
		final String jwtToken =jwtUtil.generateTokenUsingUserName(addedUser.getUserName());
		
		//generate token with user
		JwtTokenWithUser jwtTokenWithUser=new JwtTokenWithUser(addedUser,jwtToken);
		return ResponseEntity.ok(jwtTokenWithUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody JwtRequest jwtRequest){
		Users loggedInUser=usersLoginService.signIn(jwtRequest.getUserName(), jwtRequest.getPassword());
		
		
		
		if(loggedInUser!=null) {
			final String jwtToken =jwtUtil.generateTokenUsingUserName(loggedInUser.getUserName());
			return ResponseEntity.ok(jwtToken);
		}else {
			return new ResponseEntity<String>("Invalid email or password", HttpStatus.UNAUTHORIZED);
		}
	}
}
