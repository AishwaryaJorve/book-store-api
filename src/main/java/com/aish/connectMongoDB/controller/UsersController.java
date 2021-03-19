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

	/**
	 * add new user
	 * 
	 * @param userToAdd
	 * @return
	 */
	@PostMapping("/addUser")
	public ResponseEntity<JwtTokenWithUser> addUser(@RequestBody Users userToAdd) {

		// save new user
		Users addedUser = userRepository.save(userToAdd);

		// generate token using user name
		final String jwtToken = jwtUtil.generateTokenUsingUserName(addedUser.getUserName());

		// generate token with user
		JwtTokenWithUser jwtTokenWithUser = new JwtTokenWithUser(addedUser, jwtToken);

		return new ResponseEntity<JwtTokenWithUser>(jwtTokenWithUser, HttpStatus.OK);
	}

	/**
	 * login user with passing userName and password
	 * 
	 * @param jwtRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtTokenWithUser> login(@RequestBody JwtRequest jwtRequest) {

		// check user is valid or not.
		Users loggedInUser = usersLoginService.signIn(jwtRequest.getUserName(), jwtRequest.getPassword());

		if (loggedInUser != null) {

			// generate token
			final String jwtToken = jwtUtil.generateTokenUsingUserName(loggedInUser.getUserName());

			// with passing loggedUser and token fetch jwtTokenWithUser.
			JwtTokenWithUser jwtTokenWithUser = new JwtTokenWithUser(loggedInUser, jwtToken);

			return new ResponseEntity<JwtTokenWithUser>(jwtTokenWithUser, HttpStatus.OK);

		}
		return new ResponseEntity<JwtTokenWithUser>(new JwtTokenWithUser(null, null), HttpStatus.UNAUTHORIZED);

	}
}
