package model;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * File:	User.java
 * Project: LA1-MusicLibrary
 * Author:	Jacob Taylor
 * Editor:	Tristan Emma
 * Purpose:	Represents a user with its own library, a username and password, and security measures
 * 			to protect them.
 */


public class User {
	
	/*
	 * 		Instance Variables
	 */
	
	private LibraryModel library;
	private String username;
	private byte[] salt;
	private String hashedPassword;
	private boolean loggedIn;
	
	/*
	 * 		Constructor
	 */
	
	public User(String username, String password) {
		this.library = new LibraryModel();
		this.username = username;
		this.salt = getSalt();
		this.hashedPassword = hashPassword(password,this.salt);
		
		try {
			FileWriter myWriter = new FileWriter("UserInformation.txt",true);
			myWriter.write(this.username + " " + this.hashedPassword + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred writing user information: " + e.getMessage());
		}
	}
	
	public String getUsername() {
		return this.username;
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
		hashedPassword = hashedPassword.replaceAll("\\s+","");
		return hashedPassword;
	}
	
	public String searchLibrary(String s, String songOrAlbum, String titleOrArtist) {
		return this.library.search(s, songOrAlbum, titleOrArtist);
	}
	
	public boolean addSong(String songName, String artist, MusicStore musicStore) {
		return this.library.addSong(songName, artist, musicStore);
	}
	
	public boolean addAlbum(String albumName, String artist, MusicStore musicStore) {
		return this.library.addAlbum(albumName, artist, musicStore);
	}
	
	public String getSongTitles() {
		return this.library.getSongTitles();
	}
	
	public String getArtists() {
		return this.library.getArtists();
	}
	
	public String getAlbums() {
		return this.library.getAlbums();
	}
	
	public String getPlaylists() {
		return this.library.getPlaylists();
	}
	
	public String getFavoriteSongs() {
		return this.library.getFavoriteSongs();
	}
	
	public boolean createPlaylist(String playlistName) {
		return this.library.createPlaylist(playlistName);
	}
	
	public boolean addToPlaylist(String songName, String artist, String playlistName) {
		return this.library.addToPlaylist(songName, artist, playlistName);
	}
	
	public boolean removeFromPlaylist(String songName, String artist, String playlistName) {
		return this.library.removeFromPlaylist(songName, artist, playlistName);
	}
	
	public boolean checkPlaylistExistence(String playlistName) {
		return this.library.checkPlaylistExistence(playlistName);
	}
	
	public boolean checkSongInPlaylist(String songName, String artist, String playlistName) {
		return this.library.checkSongInPlaylist(songName, artist, playlistName);
	}
	
	public boolean checkSongInLibrary(String songName, String artist) {
		return this.library.checkSongInLibrary(songName, artist);
	}
	
	public void markFavorite(String songName, String artist) {
		this.library.markFavorite(songName, artist);
	}
	
	public void rateSong(String songName, String artist, int rating) {
		this.library.rateSong(songName, artist, rating);
	}
	
	public boolean isLoggedIn() {
		if (this.loggedIn) {
			return true;
		}
		return false;
	}
	
	public boolean logIn(String username, String password) {
		if (this.username.equals(username) && this.hashedPassword.equals(hashPassword(password,this.salt))) {
			this.loggedIn = true;
			return true;
		}
		
		this.loggedIn = false;
		return false;
	}
	
	public boolean logOut() {
		if (this.loggedIn) {
			this.loggedIn = false;
			return true;
		} 
		return false;
	}
	
	
}
