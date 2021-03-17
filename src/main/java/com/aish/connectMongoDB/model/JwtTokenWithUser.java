package com.aish.connectMongoDB.model;

public class JwtTokenWithUser {

	private Users user;
	private String token;

	public JwtTokenWithUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtTokenWithUser(Users user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtTokenWithUser [user=" + user + ", token=" + token + "]";
	}

}
