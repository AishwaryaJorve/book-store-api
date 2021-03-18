package com.aish.connectMongoDB.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aish.connectMongoDB.jwtUtil.JwtUtil;
import com.aish.connectMongoDB.model.JwtTokenWithUser;

@Component
@Aspect
public class TokenAspect {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Around("bookControllers()")
	public Object validToken(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
		System.out.println("inside aspect");
		//get request
		HttpServletRequest httpServletRequest=((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		
		try {
			
			//get AuthorizedToken from header of request
			String token=httpServletRequest.getHeader("AuthorizedToken");
			
			System.out.println(token);
			//check is token valid or not 
			String tokenData=jwtUtil.isValidTokenWithData(token); 
			System.out.println("Token data"+tokenData);
		}catch(Exception e) {
			
			// if token not valid send invalid token.
			return new ResponseEntity<>("Token is not valid",HttpStatus.UNAUTHORIZED);
		}
		return proceedingJoinPoint.proceed();
	}
	
	
	@Pointcut("execution(* com.aish.connectMongoDB.controller.BookController.*(..))")
	public void bookControllers() {
		
	}
	
	
}
