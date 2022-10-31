package com.udacity.jwdnd.c1.review;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private String message;
	
	public MessageService(String message) {
		System.out.println("At " + getClass().getName() +" Constructor()");
		this.message = message;
	}

	public String uppercase() {
		System.out.println("At " + getClass().getName() +" uppercase()");
		String result = message.toUpperCase();
		return result;
	}
	

	public String lowercase() {
		System.out.println("At " + getClass().getName() +" lowercase()");
		String result = message.toLowerCase();
		return result;
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("Creating MessageService bean");
	}
}
