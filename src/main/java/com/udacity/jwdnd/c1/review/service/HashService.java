package com.udacity.jwdnd.c1.review.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class HashService {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	public String getHashedValue(String data, String salt) {
		byte[] hashedValue = null;
		
		KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);
		
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hashedValue = factory.generateSecret(spec).getEncoded();
			
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return Base64.getEncoder().encodeToString(hashedValue);
	}
}
