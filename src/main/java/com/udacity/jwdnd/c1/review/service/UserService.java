package com.udacity.jwdnd.c1.review.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.c1.review.dao.UserMapper;
import com.udacity.jwdnd.c1.review.entity.User;

@Service
public class UserService {

	private final UserMapper userMapper;
	private final HashService hashService;
	
	public UserService(UserMapper userMapper, HashService hashService) {
		this.userMapper = userMapper;
		this.hashService = hashService;
	}
	
	public User getUser(String username) {
		return userMapper.getUser(username);
	}
	
	public boolean isUsernameAvailable(String username) {
		return getUser(username) == null;
	}
	
	public int createUser(User user) {
		
		// generate random salt value of 16 bytes length
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		// Use Base64 encoder to encode the salt 
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		
		// Hash the password using encodedSalt
		String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
		
		// insert new user
		User newUser = new User(user.getUsername(), encodedSalt, hashedPassword,
				user.getFirstName(), user.getLastName());
		return userMapper.addUser(newUser);
	}
}
