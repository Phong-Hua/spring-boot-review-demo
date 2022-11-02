package com.udacity.jwdnd.c1.review.service;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.c1.review.dao.UserMapper;
import com.udacity.jwdnd.c1.review.entity.User;

@Service
public class AuthenticationService implements AuthenticationProvider {

	private UserMapper userMapper;
	private HashService hashService;
	
	public AuthenticationService(UserMapper userMapper, HashService hashService) {
		this.userMapper = userMapper;
		this.hashService = hashService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// retrieve username and password from input
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		// retrieve the username from db
		User user = userMapper.getUser(username);

		if (user != null) {
			// hashed the password using encoded salt of this user
			String encodedSalt = user.getSalt();
			String hashedPassword = hashService.getHashedValue(password, encodedSalt);

			// if password match return UsernamePasswordAuthenticationToken
			if (user.getPassword().equals(hashedPassword)) {
				return new UsernamePasswordAuthenticationToken(
						username, 
						password, 
						new ArrayList<>()	// this is the list of granted authorities (permission) belong to this user
				);
			}
		}
		
		return null;
	}

	@Override
	// this method tell Spring which Authentication it can handle
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
