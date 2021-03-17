package com.aish.connectMongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.aish.connectMongoDB", "com.aish.connectMongoDB"})
public class ConnectMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectMongoDbApplication.class, args);
		System.out.println("Hello Aishwarya");
	}

}
