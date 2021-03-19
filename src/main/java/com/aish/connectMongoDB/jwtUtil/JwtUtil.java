package com.aish.connectMongoDB.jwtUtil;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.aish.connectMongoDB.model.JwtRequest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secret";

	/**
	 * generate token
	 * 
	 * @param claims
	 * @param subject
	 * @return
	 */
	public String generateTokenUsingUserName(String userName) {

		return Jwts.builder().setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	/**
	 * to check token is validate or not and return token with data.
	 * 
	 * @param token
	 * @param authenticationRequest
	 * @return
	 */
	public String isValidTokenWithData(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		String tokenData = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
		return tokenData;
	}

}
