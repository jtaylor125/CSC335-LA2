package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * File:	User.java
 * Project: LA1-MusicLibrary
 * Author:	Jacob Taylor
 * Editor:	Tristan Emma
 * Purpose:	Represents a user with its own library, a username and password, and security measures
 * 			to protect them.
 */

import java.security.SecureRandom;


public class User {
	
	/*
	 * 		Instance Variables
	 */
	
	private LibraryModel library;
	private String username;
	private byte[] salt;
	private String hashedPassword;
	
	/*
	 * 		Constructor
	 */
	
	public User(String username, String password) {
		this.library = new LibraryModel();
		this.username = username;
		this.salt = getSalt();
		this.hashedPassword = hashPassword(password,this.salt);
	}
	
	private byte[] getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	
	private String hashPassword(String password, byte[] salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
		md.update(salt);
		
		byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String hashedPassword = new String(hashedPasswordBytes, StandardCharsets.UTF_8);
		return hashedPassword;
	}
	
	public boolean logIn(String username, String password) {
		if (this.username.equals(username) && this.hashedPassword.equals(hashPassword(password,this.salt))) {
			return true;
		}
		
		return false;
	}
	
	public void logOut() {
		
	}
	
	
}
